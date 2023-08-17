package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Estacionamiento;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.EstacionamientoRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.PatenteRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.EstacionamientoService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalTimeManager;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.TimeUnitsManager;

public class EstacionamientoServiceImplementation implements EstacionamientoService {

	@Autowired
	EstacionamientoRepository estacionamientoRepository;
	@Autowired
	AutomovilistaRepository automovilistaRepository;
	@Autowired
	PatenteRepository patenteRepository;
	
	LocalTimeManager localTimeManager;
	TimeUnitsManager timeUnitsManager;
	
	public EstacionamientoServiceImplementation() {
		this.localTimeManager= new LocalTimeManager();
		this.timeUnitsManager= new TimeUnitsManager();
	}
	
	@Transactional
	public Estacionamiento crearEstacionamiento(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p) throws SistemaDeEstacionamientoException {
		//Solo para tests
		if(!this.localTimeManager.esHorarioActivo(inicio.toLocalTime())) {
			throw new SistemaDeEstacionamientoException("No es horario activo");
		}
		if (!a.puedeIniciarEstacionamiento(10d)) {
			throw new SistemaDeEstacionamientoException("No posee suficiente saldo para iniciar el estacionamiento");
		}
		Estacionamiento estacionamiento = new Estacionamiento(inicio, fin, a, p);
		LocalDateTime finDeEstacionamiento = validarFinDeEstacionamientoParaCalculoDeUnidadesDeTiempo(inicio,fin);
		int unidadesDeTiempo= calcularUnidadesDeTiempo(estacionamiento, finDeEstacionamiento);
			
			estacionamiento.setMonto(10d * unidadesDeTiempo);
			estacionamiento.setEstaActivo(false);
			estacionamiento.getAutomovilista().restarSaldo(estacionamiento.getMonto());
		return this.estacionamientoRepository.save(estacionamiento);
	}

	@Override
	public Estacionamiento iniciarEstacionamiento(Automovilista automovilista, Patente patente) throws SistemaDeEstacionamientoException {
		Optional<Automovilista> automovilistaOptional=this.automovilistaRepository.findByIdAndExistingEstacionamientoActivo(automovilista.getId());
		if (automovilistaOptional.isPresent()) {
			throw new SistemaDeEstacionamientoException("Ya posee un estacionamiento activo");
		}
		Optional<Patente> patenteOptional=this.patenteRepository.findByIdAndExistingEstacionamientoActivo(patente.getId());
		if (patenteOptional.isPresent()) {
			throw new SistemaDeEstacionamientoException("La patente ya posee un estacionamiento activo");
		}
		LocalDateTime inicio= LocalDateTime.now();
		if(!this.localTimeManager.esHorarioActivo(inicio.toLocalTime())) {
			throw new SistemaDeEstacionamientoException("No es horario activo");
		}
		if (!automovilista.puedeIniciarEstacionamiento(10d)) {
			throw new SistemaDeEstacionamientoException("No posee suficiente saldo para iniciar el estacionamiento");
		}
		Estacionamiento estacionamiento= new Estacionamiento(automovilista, patente, inicio);
		automovilista.addEstacionamiento(estacionamiento);
		patente.addEstacionamiento(estacionamiento);
		return this.estacionamientoRepository.save(estacionamiento);
	}

	@Override
	public Estacionamiento finalizarEstacionamiento(Estacionamiento estacionamiento, Double precioPorHora) {
		
		LocalDateTime finDeEstacionamiento= LocalDateTime.now();
		estacionamiento.setFinDeEstacionamiento(finDeEstacionamiento);
		int unidadesDeTiempo= calcularUnidadesDeTiempo(estacionamiento, finDeEstacionamiento);
		
		estacionamiento.setMonto(precioPorHora * unidadesDeTiempo);
		estacionamiento.setEstaActivo(false);
		estacionamiento.getAutomovilista().restarSaldo(estacionamiento.getMonto());
		return this.estacionamientoRepository.save(estacionamiento);
	}

	private int calcularUnidadesDeTiempo(Estacionamiento estacionamiento, LocalDateTime finDeEstacionamiento) {
		finDeEstacionamiento= this.validarFinDeEstacionamientoParaCalculoDeUnidadesDeTiempo(estacionamiento.getInicioDeEstacionamiento(), finDeEstacionamiento);
		return this.timeUnitsManager
			.calcularUnidadesDeTiempo(
					estacionamiento.getInicioDeEstacionamiento().toLocalTime()
					, finDeEstacionamiento.toLocalTime()
			);
	}
	
	private LocalDateTime validarFinDeEstacionamientoParaCalculoDeUnidadesDeTiempo(LocalDateTime inicioDeEstacionamiento, LocalDateTime finDeEstacionamiento) {
		LocalDateTime ldt= inicioDeEstacionamiento
			    .withHour(20)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);
		if (finDeEstacionamiento.isAfter(ldt)) {return ldt;} else {return finDeEstacionamiento;}
	}

	@Override
	public Estacionamiento conseguirEstacionamientoPorId(Long id_estacionamiento) throws SistemaDeEstacionamientoException {
		// TODO Auto-generated method stub
		Optional<Estacionamiento> estacionamientoOptional= this.estacionamientoRepository.findById(id_estacionamiento);
		if (estacionamientoOptional.isPresent()) {
			return estacionamientoOptional.get();
		}
		throw new SistemaDeEstacionamientoException("No existe la estacionamiento");
	}
	
	@Override
	public Estacionamiento conseguirEstacionamientoActivoPorTelefono(String telefono) throws SistemaDeEstacionamientoException {
		try {
			Optional<Automovilista> automovilistaOptional = this.automovilistaRepository.findByTelefono(telefono);
			if (automovilistaOptional.isPresent()) {
				Automovilista automovilista= automovilistaOptional.get();
				Optional<Estacionamiento> estacionamientoOptional=this.estacionamientoRepository.findByActivo(automovilista.getId());
				if (estacionamientoOptional.isPresent()) {
					return estacionamientoOptional.get();
				}
				throw new SistemaDeEstacionamientoException("No tiene estacionamiento activo", HttpStatus.NOT_FOUND);
			}
			throw new SistemaDeEstacionamientoException("No existe el automovilista", HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			throw e;
		}
		
	}

}

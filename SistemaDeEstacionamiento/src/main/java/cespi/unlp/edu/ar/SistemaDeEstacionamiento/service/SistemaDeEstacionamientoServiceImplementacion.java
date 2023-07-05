package cespi.unlp.edu.ar.SistemaDeEstacionamiento.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Reserva;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.ConfiguracionDelSistemaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.CuentaCorrienteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.PatenteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.ReservaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.LocalTimeManager;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.PatenteValidator;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.TimeUnitsManager;

@Transactional
public class SistemaDeEstacionamientoServiceImplementacion implements SistemaDeEstacionamientoService {

	@Autowired
	AutomovilistaRepository automovilistaRepository;
	@Autowired
	CuentaCorrienteRepository cuentaCorrienteRepository;
	@Autowired
	PatenteRepository patenteRepository;
	@Autowired
	ReservaRepository reservaRepository;
	@Autowired
	ConfiguracionDelSistemaRepository configuracionDelSistemaRepository;
	
	LocalTimeManager localTimeManager;
	TimeUnitsManager timeUnitsManager;
	
	public SistemaDeEstacionamientoServiceImplementacion() {
		this.localTimeManager= new LocalTimeManager();
		this.timeUnitsManager= new TimeUnitsManager();
	}
	
	public CuentaCorriente crearCuentaCorriente(String cbu, Double saldo) {
		CuentaCorriente cuentaCorriente = new CuentaCorriente(cbu, saldo);
		return this.cuentaCorrienteRepository.save(cuentaCorriente);
	}
	
	@Override
	public Automovilista crearAutomovilista(String telefono, String contraseña, CuentaCorriente cuentaCorriente) {
		Automovilista automovilista= new Automovilista(telefono, contraseña);
		automovilista.setCuentaCorriente(cuentaCorriente);
		//cuentaCorriente.setAutomovilista(automovilista);
		return this.automovilistaRepository.save(automovilista);
	}
	
	public Reserva crearReserva(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p) throws SistemaDeEstacionamientoException {
		//Solo para tests
		if(!this.localTimeManager.esHorarioActivo(inicio.toLocalTime())) {
			throw new SistemaDeEstacionamientoException("No es horario activo");
		}
		if (!a.puedeIniciarReserva(10d)) {
			throw new SistemaDeEstacionamientoException("No posee suficiente saldo para iniciar el estacionamiento");
		}
		Reserva reserva = new Reserva(inicio, fin, a, p);
		LocalDateTime finDeReserva = validarFinDeReservaParaCalculoDeUnidadesDeTiempo(inicio,fin);
		int unidadesDeTiempo= calcularUnidadesDeTiempo(reserva, finDeReserva);
			
			reserva.setMonto(10d * unidadesDeTiempo);
			reserva.setEstaActiva(false);
			reserva.getAutomovilista().restarSaldo(reserva.getMonto());
		return this.reservaRepository.save(reserva);
	}

	

	@Override
	public Patente agregarPatente(Automovilista automovilista, String patenteString) throws SistemaDeEstacionamientoException {
		// TODO agregar validación de formato de patente.
		if (!PatenteValidator.validarPatente(patenteString)){
			throw new SistemaDeEstacionamientoException("El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		}
		Patente patente;
		Optional<Patente> patenteOptional = patenteRepository.findByPatente(patenteString);
	    if (!patenteOptional.isPresent()) {
	    	patente=new Patente(patenteString);
	    } else {
	    	patente = patenteOptional.get();
	    }
	    automovilista.addPatente(patente);
		return this.patenteRepository.save(patente);
	}

	@Override
	public Reserva iniciarReserva(Automovilista automovilista, Patente patente) throws SistemaDeEstacionamientoException {
		//TODO validar que no exista otra reserva
		Optional<Automovilista> automovilistaOptional=this.automovilistaRepository.findByIdAndExistingReservaActiva(automovilista.getId());
		if (automovilistaOptional.isPresent()) {
			throw new SistemaDeEstacionamientoException("Ya posee una reserva activa");
		}
		Optional<Patente> patenteOptional=this.patenteRepository.findByIdAndExistingReservaActiva(patente.getId());
		if (patenteOptional.isPresent()) {
			throw new SistemaDeEstacionamientoException("La patente ya posee una reserva activa");
		}
		LocalDateTime inicio= LocalDateTime.now();
		if(!this.localTimeManager.esHorarioActivo(inicio.toLocalTime())) {
			throw new SistemaDeEstacionamientoException("No es horario activo");
		}
		if (!automovilista.puedeIniciarReserva(10d)) {
			throw new SistemaDeEstacionamientoException("No posee suficiente saldo para iniciar el estacionamiento");
		}
		Reserva reserva= new Reserva(automovilista, patente, inicio);
		automovilista.addReserva(reserva);
		patente.addReserva(reserva);
		return this.reservaRepository.save(reserva);
	}

	@Override
	public Reserva finalizarReserva(Reserva reserva, Double precioPorHora) {
		
		LocalDateTime finDeReserva= LocalDateTime.now();
		reserva.setFinDeReserva(finDeReserva);
		int unidadesDeTiempo= calcularUnidadesDeTiempo(reserva, finDeReserva);
		
		reserva.setMonto(precioPorHora * unidadesDeTiempo);
		reserva.setEstaActiva(false);
		reserva.getAutomovilista().restarSaldo(reserva.getMonto());
		return this.reservaRepository.save(reserva);
	}

	private int calcularUnidadesDeTiempo(Reserva reserva, LocalDateTime finDeReserva) {
		finDeReserva= this.validarFinDeReservaParaCalculoDeUnidadesDeTiempo(reserva.getInicioDeReserva(), finDeReserva);
		return this.timeUnitsManager
			.calcularUnidadesDeTiempo(
					reserva.getInicioDeReserva().toLocalTime()
					, finDeReserva.toLocalTime()
			);
	}
	
	private LocalDateTime validarFinDeReservaParaCalculoDeUnidadesDeTiempo(LocalDateTime inicioDeReserva, LocalDateTime finDeReserva) {
		LocalDateTime ldt= inicioDeReserva
			    .withHour(20)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);
		if (finDeReserva.isAfter(ldt)) {return ldt;} else {return finDeReserva;}
	}
	
	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) {
		ConfiguracionDelSistema cds = new ConfiguracionDelSistema( valor);
		return configuracionDelSistemaRepository.save(cds);
	}
	
	public List<Automovilista> verTodosLosAutomovilistas() {
        return (List<Automovilista>) automovilistaRepository.findAll();
    }

	@Override
	@Transactional
	public List<Automovilista> getAllAutomovilistas() {
		// TODO Auto-generated method stub
		return (List<Automovilista>) this.automovilistaRepository.findAll();
	}

}

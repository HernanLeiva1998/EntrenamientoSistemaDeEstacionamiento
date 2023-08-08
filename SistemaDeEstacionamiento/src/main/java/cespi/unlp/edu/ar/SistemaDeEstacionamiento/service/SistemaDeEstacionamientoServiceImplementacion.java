package cespi.unlp.edu.ar.SistemaDeEstacionamiento.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.ConfiguracionDelSistemaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.CuentaCorrienteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.PatenteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.EstacionamientoRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.LocalTimeManager;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.PatenteValidator;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.TimeUnitsManager;
import ch.qos.logback.core.joran.conditional.IfAction;


@Transactional
@Service
public class SistemaDeEstacionamientoServiceImplementacion implements SistemaDeEstacionamientoService {

	@Autowired
	AutomovilistaRepository automovilistaRepository;
	@Autowired
	CuentaCorrienteRepository cuentaCorrienteRepository;
	@Autowired
	PatenteRepository patenteRepository;
	@Autowired
	EstacionamientoRepository estacionamientoRepository;
	@Autowired
	ConfiguracionDelSistemaRepository configuracionDelSistemaRepository;
	
	LocalTimeManager localTimeManager;
	TimeUnitsManager timeUnitsManager;
	
	public SistemaDeEstacionamientoServiceImplementacion() {
		this.localTimeManager= new LocalTimeManager();
		this.timeUnitsManager= new TimeUnitsManager();
	}
	
	@Transactional
	public CuentaCorriente crearCuentaCorriente(String cbu, Double saldo) throws SistemaDeEstacionamientoException {
		try {
			CuentaCorriente cuentaCorriente = new CuentaCorriente(cbu, saldo);
			return this.cuentaCorrienteRepository.save(cuentaCorriente);
		} catch (Exception e) {
			throw new SistemaDeEstacionamientoException(e.getMessage());
		}
		
	}
	
	@Override
	@Transactional
	public Automovilista crearAutomovilista(String telefono, String contraseña, CuentaCorriente cuentaCorriente) 
			throws SistemaDeEstacionamientoException{
		try {
			if (this.existeAutomovilistaPorTelefono(telefono)) {
				throw new SistemaDeEstacionamientoException("Ya existe una cuenta con este teléfono");
			}
			Automovilista automovilista= new Automovilista(telefono, contraseña);
			automovilista.setCuentaCorriente(cuentaCorriente);
			return this.automovilistaRepository.save(automovilista);
			
		} catch (SistemaDeEstacionamientoException e) {
			throw e;
		}
		
		
		
	}
	
	@Override
	@Transactional
	public Automovilista crearAutomovilista(String telefono, String contraseña, String email, CuentaCorriente cuentaCorriente)
			throws SistemaDeEstacionamientoException{
		try {
			if (this.existeAutomovilistaPorTelefono(telefono)) {
				throw new SistemaDeEstacionamientoException("Ya existe una cuenta con este teléfono");
			}else if (this.existeAutomovilistaPorEmail(email)) {
				throw new SistemaDeEstacionamientoException("Ya existe una cuenta con este correo electrónico");
			}
			Automovilista automovilista= new Automovilista(telefono, contraseña, email);
			automovilista.setCuentaCorriente(cuentaCorriente);
			return this.automovilistaRepository.save(automovilista);
			
		} catch (SistemaDeEstacionamientoException e) {
			throw e;
		}
	}
	
	public boolean existeAutomovilistaPorEmail(String email) {
		return this.automovilistaRepository.findByEmail(email).isPresent();
	}
	
	public boolean existeAutomovilistaPorTelefono(String telefono) {
		return this.automovilistaRepository.findByTelefono(telefono).isPresent();
	}
	
	@Override
	public boolean existeCbu(String cbu) {
		return this.cuentaCorrienteRepository.findByCbu(cbu).isPresent();
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
	@Transactional
	public Patente agregarPatente(Automovilista automovilista, String patenteString) throws SistemaDeEstacionamientoException {
		if (!PatenteValidator.validarPatente(patenteString)){
			throw new SistemaDeEstacionamientoException("El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		}else if (automovilista.tienePatenteAsignada(patenteString)) {
			throw new SistemaDeEstacionamientoException("Esta patente ya esta agregada a la lista de patentes");	
		}
		Patente patente;
		Optional<Patente> patenteOptional = patenteRepository.findByPatente(patenteString);
	    if (!patenteOptional.isPresent()) {
	    	patente=new Patente(patenteString);
	    } else {
	    	patente = patenteOptional.get();
	    }
	    automovilista.addPatente(patente);
	    try {
	        return this.patenteRepository.save(patente);
		} catch (Exception e) {
			throw new SistemaDeEstacionamientoException("paso algo");
		}
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

	@Override
	public List<Patente> verPatentesDelAutomovilista(String telefono) throws SistemaDeEstacionamientoException {
		try {
			return this.patenteRepository.findByAutomovilistasTelefono(telefono);
		} catch (Exception e) {
			throw new SistemaDeEstacionamientoException(e.getMessage());
		}
		
	}

	public Automovilista agregarPatenteSegunTelefonoDelAutomovilista(String telefono, String patenteString) throws SistemaDeEstacionamientoException {
	    try {
	        Optional<Automovilista> automovilistaOptional = this.automovilistaRepository.findByTelefono(telefono);
	        if (automovilistaOptional.isPresent()) {
	            Automovilista automovilista = automovilistaOptional.get();
	            this.agregarPatente(automovilista, patenteString);
	            return this.automovilistaRepository.save(automovilista);
	        } else {
	            throw new SistemaDeEstacionamientoException("El automovilista no existe", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}

	@Override
	public Patente conseguirPatente(String patenteString) throws SistemaDeEstacionamientoException {
		Optional<Patente> patenteOptional = this.patenteRepository.findByPatente(patenteString);
		if (patenteOptional.isPresent()) {
			return patenteOptional.get();
		}
		throw new SistemaDeEstacionamientoException("No existe la patente");
	}

	@Override
	public Automovilista conseguirAutomovilistaPorTelefono(String telefono) throws SistemaDeEstacionamientoException {
		Optional<Automovilista> automovilistaOptional = this.automovilistaRepository.findByTelefono(telefono);
		if (automovilistaOptional.isPresent()) {
			return automovilistaOptional.get();
		}
		throw new SistemaDeEstacionamientoException("No existe el automovilista");
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
	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException{
		// TODO Auto-generated method stub
		List<ConfiguracionDelSistema> configuracionDelSistemaList= (List<ConfiguracionDelSistema>) this.configuracionDelSistemaRepository.findAll();
		if (!configuracionDelSistemaList.isEmpty()) {
			return configuracionDelSistemaList.get(0);
		}
		return  this.cambiarValorPrecioPorHora(10d);
		
	}

	@Override
	public Automovilista autenticar(String telefono, String password) throws SistemaDeEstacionamientoException {
		try {
	        Optional<Automovilista> automovilistaOptional = this.automovilistaRepository.findByTelefono(telefono);
	        if (automovilistaOptional.isPresent()) {
	        	Automovilista automovilista = automovilistaOptional.get();
	        	if (automovilista.getContraseña().equals(password))
	        		return automovilista; 
	        }
	        throw new SistemaDeEstacionamientoException("Error en el teléfono o contraseña", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        throw e;
	    }
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

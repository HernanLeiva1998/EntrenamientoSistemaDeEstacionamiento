package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.PatenteRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.PatenteService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.ValidadorPatente;

public class PatenteServiceImplementation implements PatenteService {

	@Autowired
	PatenteRepository patenteRepository;
	@Autowired
	AutomovilistaRepository automovilistaRepository;
	@Autowired
	ConfiguracionDelSistemaService configuracionDelSistemaService;
	
	@Override
	@Transactional
	public Patente agregarPatente(Automovilista automovilista, String patenteString) throws SistemaDeEstacionamientoException {
		patenteString = patenteString.toUpperCase();
		String formatosValidos = this.configuracionDelSistemaService.consequirConfiguracionDelSistema().getFormatosPatentes(); 
		if (!ValidadorPatente.validarPatente(patenteString, formatosValidos)){
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
	            throw new SistemaDeEstacionamientoException("El automovilista no existe");
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
		throw new SistemaDeEstacionamientoException("No existe la patente", HttpStatus.NO_CONTENT);
	}


}

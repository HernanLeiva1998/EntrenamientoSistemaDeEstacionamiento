package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;

public class LoginServiceImplementation implements LoginService {

	@Autowired
	AutomovilistaRepository automovilistaRepository;
	
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
}

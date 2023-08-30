package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;

public class LoginServiceImplementation implements LoginService {

	@Autowired
	DriverRepository driverRepository;
	
	@Override
	public Driver autenticar(String telefono, String password) throws ParkingSystemException {
		try {
	        Optional<Driver> automovilistaOptional = this.driverRepository.findByPhone(telefono);
	        if (automovilistaOptional.isPresent()) {
	        	Driver automovilista = automovilistaOptional.get();
	        	if (automovilista.getContrasena().equals(password))
	        		return automovilista; 
	        }
	        throw new ParkingSystemException("Error en el teléfono o contraseña");
	    } catch (Exception e) {
	        throw e;
	    }
	}
}

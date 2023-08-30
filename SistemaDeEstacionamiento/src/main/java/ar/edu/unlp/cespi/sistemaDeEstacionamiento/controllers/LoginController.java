package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.LoginDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;

@RestController
public class LoginController {
	@Autowired
    private LoginService service;
	//private SistemaDeEstacionamientoService service;
	
	@PostMapping("/login")
	   @CrossOrigin(origins = "http://localhost:4200")
	    public ResponseEntity<?> autenticar(@RequestBody LoginDTO request) throws ParkingSystemException {
		
	            Driver automovilista = service.autenticar(request.getTelefono(), request.getContrasena());
	  
	            return ResponseEntity.ok().body(automovilista);

	        
	    }
}

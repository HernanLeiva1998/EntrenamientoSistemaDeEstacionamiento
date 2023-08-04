package cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers.dtos.ErrorDTO;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers.dtos.LoginDTO;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

@RestController
public class LoginController {
	@Autowired
    private SistemaDeEstacionamientoService service;
	
	@PostMapping("/login")
	   @CrossOrigin(origins = "http://localhost:4200")
<<<<<<< HEAD
	    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody LoginDTO request) throws SistemaDeEstacionamientoException {
		
	            Automovilista automovilista = service.autenticar(request.getTelefono(), request.getContrasena());
	  
	            return ResponseEntity.ok().body(automovilista);
=======
	    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody Map<String, String> request) throws SistemaDeEstacionamientoException {
	        String password = request.get("contrasena");
	        String telefono = request.get("telefono");
	        
	        Automovilista automovilista = service.autenticar(telefono, password);
	            
	        return ResponseEntity.ok().body(automovilista);
>>>>>>> 808c66d00b8631376604434b49c6fbf9609bc6d9
	        
	    }
}

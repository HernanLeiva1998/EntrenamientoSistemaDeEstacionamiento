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
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@RestController
public class LoginController {
	@Autowired
    private SistemaDeEstacionamientoService service;
	
	@PostMapping("/login")
	   @CrossOrigin(origins = "http://localhost:4200")
	    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody Map<String, String> request) {
	        String password = request.get("contrasena");
	        String telefono = request.get("telefono");
	        try {
	            Automovilista automovilista = service.autenticar(telefono, password);
	            
	            return ResponseEntity.ok().body(automovilista);
	        } catch (SistemaDeEstacionamientoException e) {
	        	if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDTO(e.getMessage()));
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
	        }
	    }
}

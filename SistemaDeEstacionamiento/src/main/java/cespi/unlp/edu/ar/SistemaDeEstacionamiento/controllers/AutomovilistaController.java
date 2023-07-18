package cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@RestController
public class AutomovilistaController {

	@Autowired
	SistemaDeEstacionamientoService service;
	
	@GetMapping("/api/automovilistas")
	public List<Automovilista> getAllAutomovilistas() {
		return this.service.getAllAutomovilistas();
	}
	
	@GetMapping("/api/automovilistas/buscar/{telefono}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAutomovilistaPorTelefono(@PathVariable String telefono) {
        try {
        	return ResponseEntity.ok().body(service.conseguirAutomovilistaPorTelefono(telefono));
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
}

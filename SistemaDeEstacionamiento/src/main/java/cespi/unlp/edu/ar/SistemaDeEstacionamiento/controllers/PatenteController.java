package cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@RestController
public class PatenteController {

    @Autowired
    private SistemaDeEstacionamientoService service;

    @GetMapping("/api/patentes/buscar/{telefono}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getPatentesPorTelefono(@PathVariable String telefono) {
    	try {
    		return ResponseEntity.ok(service.verPatentesDelAutomovilista(telefono));
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
        
    }

    @PostMapping("/api/patentes/agregar")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody Map<String, String> request) {
        String patente = request.get("patente");
        String telefono = request.get("telefono");
        try {
            Automovilista automovilista = service.agregarPatenteSegunTelefonoDelAutomovilista(telefono, patente);
            
            return ResponseEntity.created(null).body(automovilista);
        } catch (SistemaDeEstacionamientoException e) {
        	if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
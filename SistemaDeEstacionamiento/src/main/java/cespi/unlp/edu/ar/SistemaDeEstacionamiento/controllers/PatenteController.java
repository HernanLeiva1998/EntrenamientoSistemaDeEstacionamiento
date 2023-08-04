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

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers.dtos.ErrorDTO;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

@RestController
public class PatenteController {

    @Autowired
    private SistemaDeEstacionamientoService service;

    @GetMapping("/api/patentes/buscar/{telefono}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getPatentesPorTelefono(@PathVariable String telefono) throws SistemaDeEstacionamientoException {
    	
    	return ResponseEntity.ok(service.verPatentesDelAutomovilista(telefono));
		
        
    }

    @PostMapping("/api/patentes/agregar")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody Map<String, String> request) throws SistemaDeEstacionamientoException {
        String patente = request.get("patente");
        String telefono = request.get("telefono");
        Automovilista automovilista = service.agregarPatenteSegunTelefonoDelAutomovilista(telefono, patente);
        
        return ResponseEntity.created(null).body(automovilista);
        
    }
}
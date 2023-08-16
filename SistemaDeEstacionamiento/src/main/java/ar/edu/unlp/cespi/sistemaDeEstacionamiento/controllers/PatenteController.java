package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

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

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.ErrorDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.PatenteDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

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
    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody PatenteDTO request) throws SistemaDeEstacionamientoException {
        
        Automovilista automovilista = service.agregarPatenteSegunTelefonoDelAutomovilista(request.getTelefono(), request.getPatente());
        
        return ResponseEntity.created(null).body(automovilista);
        
    }
}
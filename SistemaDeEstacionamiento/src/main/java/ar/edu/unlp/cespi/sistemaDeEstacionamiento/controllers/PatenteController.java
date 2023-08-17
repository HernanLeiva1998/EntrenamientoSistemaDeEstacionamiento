package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.PatenteDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.PatenteService;

@RestController
public class PatenteController {

    @Autowired
    private PatenteService service;//SistemaDeEstacionamientoService service;

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
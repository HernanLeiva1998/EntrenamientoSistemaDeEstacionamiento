package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.AutomovilistaRequest;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PruebaController {
	
	@Autowired
	SistemaDeEstacionamientoService service;
	
	@GetMapping("/automovilistas")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<Automovilista> obtenerTodosLosAutomovilistas() {
        return service.verTodosLosAutomovilistas();
    }

}

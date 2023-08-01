package cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers.dtos.AutomovilistaRequest;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

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

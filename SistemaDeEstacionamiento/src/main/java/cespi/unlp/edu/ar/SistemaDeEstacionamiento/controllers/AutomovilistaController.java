package cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

@RestController
public class AutomovilistaController {

	@Autowired
	SistemaDeEstacionamientoService service;
	
	@GetMapping("/")
	public List<Automovilista> getAllAutomovilistas() {
		return this.service.getAllAutomovilistas();
	}
}

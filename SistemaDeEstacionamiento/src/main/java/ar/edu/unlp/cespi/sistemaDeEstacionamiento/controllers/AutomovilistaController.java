package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.NuevoAutomovilistaDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;

@RestController
public class AutomovilistaController {

	@Autowired
	AutomovilistaService service;
	
	
	
	@GetMapping("/api/automovilistas/buscar/{telefono}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAutomovilistaPorTelefono(@PathVariable String telefono) throws SistemaDeEstacionamientoException {
		((Automovilista)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTelefono();
		return ResponseEntity.ok().body(service.conseguirAutomovilistaPorTelefono(telefono));
    }
	
	@PostMapping("api/automovilistas/crear")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity<?> crearAutomovilista(@RequestBody NuevoAutomovilistaDTO nuevoAutomovilista) throws SistemaDeEstacionamientoException {
    	CuentaCorriente cc = service.crearCuentaCorriente(1000d);
    	service.crearAutomovilista(
	    		nuevoAutomovilista.getTelefono(),
	    		nuevoAutomovilista.getContrasena(),
	    		nuevoAutomovilista.getEmail(),
	    		cc,
	    		Role.USER
	    );
    	return ResponseEntity.ok().build();
	  }
}

package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.IniciarEstacionamientoDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Estacionamiento;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.EstacionamientoService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.PatenteService;

@RestController
public class EstacionamientoController {
	@Autowired
	private EstacionamientoService service;
	@Autowired 
	private AutomovilistaService automovilistaService;
    @Autowired
    private PatenteService patenteService;
    @Autowired 
    private ConfiguracionDelSistemaService configuracionDelSistemaService;
	
	@PostMapping("/api/estacionamientos/iniciar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> iniciarEstacionamiento(@RequestBody IniciarEstacionamientoDTO request) throws SistemaDeEstacionamientoException{
	
		Estacionamiento estacionamiento = this.service.iniciarEstacionamiento(
				this.automovilistaService.conseguirAutomovilistaPorTelefono(request.getTelefono()),
				this.patenteService.conseguirPatente(request.getPatente())
			);
		return ResponseEntity.created(null).body(estacionamiento);
		
	}
	@PutMapping("/api/estacionamientos/finalizar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> finalizarEstacionamiento(@RequestBody Map<String,String> request) throws SistemaDeEstacionamientoException{

		Estacionamiento estacionamientoFinalizada = this.service.finalizarEstacionamiento(
				this.service.conseguirEstacionamientoActivoPorTelefono(request.get("telefono")),
				this.configuracionDelSistemaService.consequirConfiguracionDelSistema().getPrecioPorHora()
			);
		return ResponseEntity.ok().body(estacionamientoFinalizada);
		
	}
	
	@GetMapping("/api/estacionamientos/{id_estacionamiento}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> conseguirEstacionamiento(@PathVariable String id_estacionamiento) throws SistemaDeEstacionamientoException{
		Estacionamiento estacionamiento =this.service.conseguirEstacionamientoPorId(Long.decode(id_estacionamiento));
		return ResponseEntity.ok().body(estacionamiento);
		
	}
	
	@GetMapping("/api/estacionamientos/estacionamientoActivo/{telefono}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> conseguirEstacionamientoActivo(@PathVariable String telefono) throws SistemaDeEstacionamientoException{
		Estacionamiento estacionamiento =this.service.conseguirEstacionamientoActivoPorTelefono(telefono);
		return ResponseEntity.ok().body(estacionamiento);
		
	}
	
}

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
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers.dtos.iniciarEstacionamientoDTO;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

@RestController
public class EstacionamientoController {
	@Autowired
    private SistemaDeEstacionamientoService service;
	
	@PostMapping("/api/estacionamientos/iniciar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> iniciarEstacionamiento(@RequestBody iniciarEstacionamientoDTO request) throws SistemaDeEstacionamientoException{
	
		Estacionamiento estacionamiento = this.service.iniciarEstacionamiento(
				this.service.conseguirAutomovilistaPorTelefono(request.getTelefono()),
				this.service.conseguirPatente(request.getPatente())
			);
		return ResponseEntity.created(null).body(estacionamiento);
		
	}
	@PutMapping("/api/estacionamientos/finalizar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> finalizarEstacionamiento(@RequestBody Map<String,String> request) throws SistemaDeEstacionamientoException{

		Estacionamiento estacionamientoFinalizada = this.service.finalizarEstacionamiento(
				this.service.conseguirEstacionamientoActivoPorTelefono(request.get("telefono")),
				this.service.consequirConfiguracionDelSistema().getPrecioPorHora()
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

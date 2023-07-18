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
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@RestController
public class EstacionamientoController {
	@Autowired
    private SistemaDeEstacionamientoService service;
	
	@PostMapping("/api/estacionamientos/iniciar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> iniciarEstacionamiento(@RequestBody Map<String, String> request){
		try {
			Patente patente= this.service.conseguirPatente(request.get("patente"));
			Automovilista automovilista = this.service.conseguirAutomovilistaPorTelefono(request.get("telefono"));
			Estacionamiento estacionamiento = this.service.iniciarEstacionamiento(automovilista, patente);
			return ResponseEntity.created(null).body(estacionamiento);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PutMapping("/api/estacionamientos/finalizar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> finalizarEstacionamiento(@RequestBody Map<String,Long> request){
		try {
			Double precioPorHora = this.service.consequirConfiguracionDelSistema().getPrecioPorHora();
			Estacionamiento estacionamiento =this.service.conseguirEstacionamientoPorId(request.get("id_estacionamiento"));
			Estacionamiento estacionamientoFinalizada = this.service.finalizarEstacionamiento(estacionamiento, precioPorHora);
			return ResponseEntity.ok().body(estacionamientoFinalizada);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/api/estacionamientos/{id_estacionamiento}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> conseguirEstacionamiento(@PathVariable String id_estacionamiento){
		try {
			Estacionamiento estacionamiento =this.service.conseguirEstacionamientoPorId(Long.decode(id_estacionamiento));
			return ResponseEntity.ok().body(estacionamiento);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/api/estacionamientos/estacionamientoActivo/{telefono}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> conseguirEstacionamientoActivo(@PathVariable String telefono){
		try {
			Estacionamiento estacionamiento =this.service.conseguirEstacionamientoActivoPorTelefono(telefono);
			return ResponseEntity.ok().body(estacionamiento);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}

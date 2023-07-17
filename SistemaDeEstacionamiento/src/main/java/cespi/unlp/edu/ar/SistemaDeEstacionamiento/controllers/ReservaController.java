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
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Reserva;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@RestController
public class ReservaController {
	@Autowired
    private SistemaDeEstacionamientoService service;
	
	@PostMapping("/reservas/iniciar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> iniciarReserva(@RequestBody Map<String, String> request){
		try {
			Patente patente= this.service.conseguirPatente(request.get("patente"));
			Automovilista automovilista = this.service.conseguirAutomovilistaPorTelefono(request.get("telefono"));
			Reserva reserva = this.service.iniciarReserva(automovilista, patente);
			return ResponseEntity.created(null).body(reserva);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PutMapping("/reservas/finalizar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> finalizarReserva(@RequestBody Map<String,Long> request){
		try {
			Double precioPorHora = this.service.consequirConfiguracionDelSistema().getPrecioPorHora();
			Reserva reserva =this.service.conseguirReservaPorId(request.get("id_reserva"));
			Reserva reservaFinalizada = this.service.finalizarReserva(reserva, precioPorHora);
			return ResponseEntity.ok().body(reservaFinalizada);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/reservas/{id_reserva}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> conseguirReserva(@PathVariable String id_reserva){
		try {
			Reserva reserva =this.service.conseguirReservaPorId(Long.decode(id_reserva));
			return ResponseEntity.ok().body(reserva);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/reservas/reservaActiva/{telefono}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> conseguirReservaActiva(@PathVariable String telefono){
		try {
			Reserva reserva =this.service.conseguirReservaActivaPorTelefono(telefono);
			return ResponseEntity.ok().body(reserva);
		} catch (SistemaDeEstacionamientoException e) {
			if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}

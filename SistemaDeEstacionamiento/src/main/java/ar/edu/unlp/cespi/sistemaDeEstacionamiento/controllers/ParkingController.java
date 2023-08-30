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

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.StartParkingDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ParkingService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LicensePlateService;

@RestController
public class ParkingController {
	@Autowired
	private ParkingService service;
	@Autowired 
	private DriverService driverService;
    @Autowired
    private LicensePlateService licensePlateService;
    @Autowired 
    private ConfiguracionDelSistemaService configuracionDelSistemaService;
	
	@PostMapping("/api/estacionamientos/iniciar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> startParking(@RequestBody StartParkingDTO request) throws ParkingSystemException{
	
		Parking parking = this.service.startParking(
				this.driverService.getDriverByPhone(request.getPhone()),
				this.licensePlateService.getLicensePlate(request.getLicensePlate())
			);
		return ResponseEntity.created(null).body(parking);
		
	}
	@PutMapping("/api/estacionamientos/finalizar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> endParking(@RequestBody Map<String,String> request) throws ParkingSystemException{

		Parking parkingFinalizada = this.service.endParking(
				this.service.getActiveParkingByPhone(request.get("telefono")),
				this.configuracionDelSistemaService.consequirConfiguracionDelSistema().getPrecioPorHora()
			);
		return ResponseEntity.ok().body(parkingFinalizada);
		
	}
	
	@GetMapping("/api/estacionamientos/{id_parking}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getParking(@PathVariable String id_parking) throws ParkingSystemException{
		Parking parking =this.service.getParkingById(Long.decode(id_parking));
		return ResponseEntity.ok().body(parking);
		
	}
	
	@GetMapping("/api/estacionamientos/estacionamientoActivo/{telefono}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getActiveParking(@PathVariable String telefono) throws ParkingSystemException{
		Parking parking =this.service.getActiveParkingByPhone(telefono);
		return ResponseEntity.ok().body(parking);
		
	}
	
}

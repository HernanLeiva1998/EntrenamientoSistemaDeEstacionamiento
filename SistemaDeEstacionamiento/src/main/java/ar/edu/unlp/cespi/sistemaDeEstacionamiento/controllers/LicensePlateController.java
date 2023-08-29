package ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.LicensePlateDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LicensePlateService;

@RestController
public class LicensePlateController {

    @Autowired
    private LicensePlateService service;//SistemaDeEstacionamientoService service;

    @GetMapping("/api/patentes/buscar/{telefono}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getLicensePlatesByPhone(@PathVariable String telefono) throws ParkingSystemException {
    	
    	return ResponseEntity.ok(service.getDriverLicensePlatesList(telefono));
		
        
    }

    @PostMapping("/api/patentes/agregar")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> addLicentePlateToDriver(@RequestBody LicensePlateDTO request) throws ParkingSystemException {
        
        Driver automovilista = service.addLicensePlateToDriverByPhone(request.getPhone(), request.getLicensePlate());
        
        return ResponseEntity.created(null).body(automovilista);
        
    }
}
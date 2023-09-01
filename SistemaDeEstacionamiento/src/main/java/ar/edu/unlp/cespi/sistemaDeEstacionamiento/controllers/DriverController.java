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

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.NewDriverDTO;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;

@RestController
public class DriverController {

	@Autowired
	DriverService service;
	
	private String getDriverPhoneFromSecurityContextHolder() {
		return ((Driver)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhone();
	}
	
	@GetMapping("/api/automovilistas/buscar")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getDriverByPhone() throws ParkingSystemException {
		return ResponseEntity
				.ok()
				.body(
						service.getDriverByPhone(this.getDriverPhoneFromSecurityContextHolder())
				);
    }	
		
	
	@PostMapping("api/automovilistas/crear")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity<?> createDriver(@RequestBody NewDriverDTO newDriver) throws ParkingSystemException {
    	Wallet cc = service.createWallet(1000d);
    	service.createDriver(
	    		newDriver.getPhone(),
	    		newDriver.getPassword(),
	    		newDriver.getEmail(),
	    		cc,
	    		Role.USER
	    );
    	return ResponseEntity.ok().build();
	  }
}

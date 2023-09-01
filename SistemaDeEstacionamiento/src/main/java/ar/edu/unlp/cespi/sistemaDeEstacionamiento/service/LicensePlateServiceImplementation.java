package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.LicensePlateRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.SystemConfigService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LicensePlateService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LicensePlateValidator;

public class LicensePlateServiceImplementation implements LicensePlateService {

	@Autowired
	LicensePlateRepository licensePlateRepository;
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	SystemConfigService configuracionDelSistemaService;
	
	@Override
	@Transactional
	public LicensePlate addLicensePlate(Driver driver, String licensePlateString) throws ParkingSystemException {
		licensePlateString = licensePlateString.toUpperCase();
		String validFormats = this.configuracionDelSistemaService.getSystemConfig().getFormatosPatentes(); 
		if (!LicensePlateValidator.validateLicensePlate(licensePlateString, validFormats)){
			throw new ParkingSystemException("El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		}else if (driver.hasLicensePlateAssigned(licensePlateString)) {
			throw new ParkingSystemException("Esta patente ya esta agregada a la lista de patentes");	
		}
		
		LicensePlate licensePlate;
		Optional<LicensePlate> licensePlateOptional = licensePlateRepository.findByLicensePlate(licensePlateString);
	    if (!licensePlateOptional.isPresent()) {
	    	licensePlate=new LicensePlate(licensePlateString);
	    } else {
	    	licensePlate = licensePlateOptional.get();
	    }
	    driver.addLicensePlate(licensePlate);
	    try {
	        return this.licensePlateRepository.save(licensePlate);
		} catch (Exception e) {
			throw new ParkingSystemException("paso algo");
		}
	}

	@Override
	public List<LicensePlate> getDriverLicensePlatesList(String telefono) throws ParkingSystemException {
		try {
			return this.licensePlateRepository.findByDriversPhone(telefono);
		} catch (Exception e) {
			throw new ParkingSystemException(e.getMessage());
		}
		
	}

	public Driver addLicensePlateToDriverByPhone(String telefono, String licensePlateString) throws ParkingSystemException {
	    try {
	        Optional<Driver> driverOptional = this.driverRepository.findByPhone(telefono);
	        if (driverOptional.isPresent()) {
	            Driver driver = driverOptional.get();
	            this.addLicensePlate(driver, licensePlateString);
	            return this.driverRepository.save(driver);
	        } else {
	            throw new ParkingSystemException("El automovilista no existe");
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}

	@Override
	public LicensePlate getLicensePlate(String licensePlateString) throws ParkingSystemException {
		Optional<LicensePlate> licensePlateOptional = this.licensePlateRepository.findByLicensePlate(licensePlateString);
		if (licensePlateOptional.isPresent()) {
			return licensePlateOptional.get();
		}
		throw new ParkingSystemException("No existe la patente");
	}


}

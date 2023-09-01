package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;

@Service
public interface LicensePlateService {

	public LicensePlate addLicensePlate(Driver driver, String licensePlate) throws ParkingSystemException;
	
	public List<LicensePlate> getDriverLicensePlatesList(String phone) throws ParkingSystemException;

	public Driver addLicensePlateToDriverByPhone(String phone, String licensePlate) throws ParkingSystemException;
	
	public LicensePlate getLicensePlate(String licensePlate) throws ParkingSystemException;

}

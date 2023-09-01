package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalDateTimeProvider;

@Service
public interface ParkingService {
	public Parking createParkingWithStartAndEnd(LocalDateTime start, LocalDateTime end, Driver d, LicensePlate lp) throws ParkingSystemException;
	
	public Parking startParking(Driver driver, LicensePlate licensePlate) throws ParkingSystemException;

	public Parking endParking(Parking parking, Double pricePerHour) throws ParkingSystemException;
	
	public Parking getParkingById(Long id_parking) throws ParkingSystemException;

	public Parking getActiveParkingByPhone(String phone)  throws ParkingSystemException;

	void changeLocalDateTimeProvider(LocalDateTimeProvider ldtp);

}

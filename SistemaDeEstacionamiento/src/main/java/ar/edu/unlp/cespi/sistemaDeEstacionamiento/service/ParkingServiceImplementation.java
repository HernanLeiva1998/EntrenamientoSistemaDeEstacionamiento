package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.ParkingRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.LicensePlateRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ParkingService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalDateTimeProvider;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalDateTimeProviderImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalTimeManager;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.TimeUnitsManager;

public class ParkingServiceImplementation implements ParkingService {

	@Autowired
	ParkingRepository parkingRepository;
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	LicensePlateRepository licensePlateRepository;
	
	LocalTimeManager localTimeManager;
	TimeUnitsManager timeUnitsManager;
	LocalDateTimeProvider localDateTimeProvider;
	
	public ParkingServiceImplementation(LocalDateTimeProvider ldtp) {
		this.localTimeManager= new LocalTimeManager();
		this.timeUnitsManager= new TimeUnitsManager();
		this.localDateTimeProvider = ldtp;
	}
	
	public ParkingServiceImplementation() {
		this(new LocalDateTimeProviderImplementation());
	}
	
	@Override
	public void changeLocalDateTimeProvider(LocalDateTimeProvider ldtp) {
		this.localDateTimeProvider=ldtp;
	}
	
	@Transactional
	public Parking createParkingWithStartAndEnd(LocalDateTime start, LocalDateTime end, Driver a, LicensePlate lp) throws ParkingSystemException {
		//Solo para tests
		if(!this.localTimeManager.isActiveTime(start.toLocalTime())) {
			throw new ParkingSystemException("No es horario activo");
		}
		if (!a.canStartParking(10d)) {
			throw new ParkingSystemException("No posee suficiente saldo para iniciar el estacionamiento");
		}
		Parking parking = new Parking(start, end, a, lp);
		LocalDateTime finDeEstacionamiento = validateEndOfParkingForTimeUnitsCalculation(start,end);
		int unidadesDeTiempo= calculateTimeUnits(parking, finDeEstacionamiento);
			
			parking.setTotalCost(10d * unidadesDeTiempo);
			parking.setIsActive(false);
			parking.getDriver().subtractBalance(parking.getTotalCost());
		return this.parkingRepository.save(parking);
	}

	@Override
	public Parking startParking(Driver driver, LicensePlate licensePlate) throws ParkingSystemException {
		Optional<Driver> driverOptional=this.driverRepository.findDriverByIdAndExistsActiveParking(driver.getId());
		if (driverOptional.isPresent()) {
			throw new ParkingSystemException("Ya posee un estacionamiento activo");
		}
		Optional<LicensePlate> licensePlateOptional=this.licensePlateRepository.findByIdAndExistsActiveParking(licensePlate.getId());
		if (licensePlateOptional.isPresent()) {
			throw new ParkingSystemException("La patente ya posee un estacionamiento activo");
		}
		LocalDateTime inicio= LocalDateTime.now();
		if(!this.localTimeManager.isActiveTime(inicio.toLocalTime())) {
			throw new ParkingSystemException("No es horario activo");
		}
		if (!driver.canStartParking(10d)) {
			throw new ParkingSystemException("No posee suficiente saldo para iniciar el estacionamiento");
		}
		Parking parking= new Parking(driver, licensePlate, inicio);
		driver.addParking(parking);
		licensePlate.addParking(parking);
		return this.parkingRepository.save(parking);
	}

	@Override
	public Parking endParking(Parking parking, Double pricePerHour) {
		
		LocalDateTime parkingEnd= LocalDateTime.now();
		parking.setParkingEnd(parkingEnd);
		int timeUnits= calculateTimeUnits(parking, parkingEnd);
		
		parking.setTotalCost(pricePerHour * timeUnits);
		parking.setIsActive(false);
		parking.getDriver().subtractBalance(parking.getTotalCost());
		return this.parkingRepository.save(parking);
	}

	private int calculateTimeUnits(Parking parking, LocalDateTime endOfParking) {
		endOfParking= this.validateEndOfParkingForTimeUnitsCalculation(parking.getParkingStart(), endOfParking);
		return this.timeUnitsManager
			.calcularUnidadesDeTiempo(
					parking.getParkingStart().toLocalTime()
					, endOfParking.toLocalTime()
			);
	}
	
	private LocalDateTime validateEndOfParkingForTimeUnitsCalculation(LocalDateTime parkingStart, LocalDateTime parkingEnd) {
		LocalDateTime ldt= parkingStart
			    .withHour(20)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);
		if (parkingEnd.isAfter(ldt)) {return ldt;} else {return parkingEnd;}
	}

	@Override
	public Parking getParkingById(Long id_parking) throws ParkingSystemException {
		// TODO Auto-generated method stub
		Optional<Parking> parkingOptional= this.parkingRepository.findById(id_parking);
		if (parkingOptional.isPresent()) {
			return parkingOptional.get();
		}
		throw new ParkingSystemException("No existe la estacionamiento");
	}
	
	@Override
	public Parking getActiveParkingByPhone(String telefono) throws ParkingSystemException {
		try {
			Optional<Driver> driverOptional = this.driverRepository.findByPhone(telefono);
			if (driverOptional.isPresent()) {
				Driver driver= driverOptional.get();
				Optional<Parking> parkingOptional=this.parkingRepository.findByActive(driver.getId());
				if (parkingOptional.isPresent()) {
					return parkingOptional.get();
				}
				throw new ParkingSystemException("No tiene estacionamiento activo");
			}
			throw new ParkingSystemException("No existe el automovilista");
			
		} catch (Exception e) {
			throw e;
		}
		
	}

}

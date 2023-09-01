package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.WalletRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.ParkingRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.LicensePlateRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;

@DataJpaTest
class AutomovilistaRepositoryTest {

	@Autowired
	DriverRepository driverRepository;
	@Autowired
	LicensePlateRepository licensePlateRepository;
	@Autowired
	WalletRepository walletRepository;
	@Autowired 
	ParkingRepository parkingRepository;
	
	Driver automovilista;
	LicensePlate patente;
	Parking estacionamiento;
	LocalDateTime horaInicio, horaFin;
	
	@BeforeEach
	void setUp() {
		horaInicio = LocalDateTime.now()
			    .withHour(8)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);
		horaFin = LocalDateTime.now()
			    .withHour(9)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);
		automovilista = new Driver("0001112222", "1234");
		automovilista.setWallet(this.walletRepository.save(new Wallet(1000d)));
		patente= this.licensePlateRepository.save(new LicensePlate("AAA999"));
		automovilista.addLicensePlate(patente);
		estacionamiento=new Parking(automovilista, patente, horaInicio);
	}
	
	@Test
	void debeEncontrarAlAutomovilsitaPorEstacionamientoActivoTest() {
		//Given
		estacionamiento = this.parkingRepository.save(estacionamiento);
		automovilista.addParking(estacionamiento);
		this.driverRepository.save(automovilista);
		
		//When
		Optional<Driver> automovilistaOptional = this.driverRepository.findDriverByIdAndExistsActiveParking(automovilista.getId());
		//then
		assertThat(automovilistaOptional).isPresent();
		assertThat(automovilistaOptional.get()).usingRecursiveComparison().isEqualTo(automovilista);
	}
	
	@Test
	void noDebeEncontrarAlAutomovilsitaPorEstacionamientoActivoTest() {
		//When
		Optional<Driver> automovilistaOptional = this.driverRepository.findDriverByIdAndExistsActiveParking(automovilista.getId());
		//then
		assertThat(automovilistaOptional).isEmpty();
		
		//and Given
		estacionamiento.setParkingEnd(horaFin);
		estacionamiento.setIsActive(false);
		estacionamiento = this.parkingRepository.save(estacionamiento);
		automovilista.addParking(estacionamiento);
		automovilista= this.driverRepository.save(automovilista);
		
		//When
		automovilistaOptional= this.driverRepository.findDriverByIdAndExistsActiveParking(automovilista.getId());
		//then
		assertThat(automovilistaOptional).isEmpty();
		
	}
	
	@AfterEach
	void tearDown() {
		parkingRepository.deleteAll();
		licensePlateRepository.deleteAll();
		driverRepository.deleteAll();
		walletRepository.deleteAll();
	}

}

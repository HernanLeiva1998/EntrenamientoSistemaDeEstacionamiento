package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.WalletRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.ParkingRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.LicensePlateRepository;

@DataJpaTest
class EstacionamientoRepositoryTest {

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
	void setUp() throws Exception {
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
		driverRepository.save(automovilista);
		estacionamiento=new Parking(automovilista, patente, horaInicio);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindByActivo() {
		Optional<Parking> estacionamientoOptional = this.parkingRepository.findByActive(estacionamiento.getId());
		assertThat(estacionamientoOptional).isEmpty();
		
		parkingRepository.save(estacionamiento);
		estacionamientoOptional = this.parkingRepository.findByActive(estacionamiento.getId());
		assertThat(estacionamientoOptional).isPresent();
		assertThat(estacionamientoOptional.get()).usingRecursiveComparison().isEqualTo(estacionamiento);
		
		estacionamiento.setParkingEnd(horaFin);
		estacionamiento.setIsActive(false);
		estacionamiento = this.parkingRepository.save(estacionamiento);
		
		estacionamientoOptional = this.parkingRepository.findByActive(estacionamiento.getId());
		assertThat(estacionamientoOptional).isEmpty();
		
	}

}

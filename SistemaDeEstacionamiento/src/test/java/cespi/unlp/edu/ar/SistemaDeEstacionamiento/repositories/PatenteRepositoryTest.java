package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;

@DataJpaTest
class PatenteRepositoryTest {

	@Autowired
	AutomovilistaRepository automovilistaRepository;
	@Autowired
	PatenteRepository patenteRepository;
	@Autowired
	CuentaCorrienteRepository cuentaCorrienteRepository;
	@Autowired 
	EstacionamientoRepository estacionamientoRepository;
	
	Automovilista automovilista;
	Patente patente;
	Estacionamiento estacionamiento;
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
		automovilista = new Automovilista("0001112222", "1234");
		automovilista.setCuentaCorriente(this.cuentaCorrienteRepository.save(new CuentaCorriente("01234567890123456", 1000d)));
		patente= this.patenteRepository.save(new Patente("AAA999"));
		automovilista.addPatente(patente);
		automovilistaRepository.save(automovilista);
		estacionamiento=new Estacionamiento(automovilista, patente, horaInicio);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		estacionamientoRepository.deleteAll();
		patenteRepository.deleteAll();
		automovilistaRepository.deleteAll();
		cuentaCorrienteRepository.deleteAll();
	}

	@Test
	void testFindByIdAndExistingEstacionamientoActivo() {
		
		Optional<Patente> patenteOptional= this.patenteRepository.findByIdAndExistingEstacionamientoActivo(patente.getId());
		assertThat(patenteOptional).isEmpty();
		
		estacionamiento = this.estacionamientoRepository.save(estacionamiento);
		patente.addEstacionamiento(estacionamiento);
		patente= this.patenteRepository.save(patente);
		
		patenteOptional= this.patenteRepository.findByIdAndExistingEstacionamientoActivo(patente.getId());
		assertThat(patenteOptional).isPresent();
		assertThat(patenteOptional.get()).usingRecursiveComparison().isEqualTo(patente);
		
		estacionamiento.setFinDeEstacionamiento(horaFin);
		estacionamiento.setEstaActivo(false);
		estacionamiento = this.estacionamientoRepository.save(estacionamiento);
		
		patenteOptional= this.patenteRepository.findByIdAndExistingEstacionamientoActivo(patente.getId());
		assertThat(patenteOptional).isEmpty();
		
	}

}

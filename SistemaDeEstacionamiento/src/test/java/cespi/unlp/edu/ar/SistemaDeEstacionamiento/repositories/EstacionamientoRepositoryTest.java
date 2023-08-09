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
class EstacionamientoRepositoryTest {

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
	}

	@Test
	void testFindByActivo() {
		Optional<Estacionamiento> estacionamientoOptional = this.estacionamientoRepository.findByActivo(estacionamiento.getId());
		assertThat(estacionamientoOptional).isEmpty();
		
		estacionamientoRepository.save(estacionamiento);
		estacionamientoOptional = this.estacionamientoRepository.findByActivo(estacionamiento.getId());
		assertThat(estacionamientoOptional).isPresent();
		assertThat(estacionamientoOptional.get()).usingRecursiveComparison().isEqualTo(estacionamiento);
		
		estacionamiento.setFinDeEstacionamiento(horaFin);
		estacionamiento.setEstaActivo(false);
		estacionamiento = this.estacionamientoRepository.save(estacionamiento);
		
		estacionamientoOptional = this.estacionamientoRepository.findByActivo(estacionamiento.getId());
		assertThat(estacionamientoOptional).isEmpty();
		
	}

}

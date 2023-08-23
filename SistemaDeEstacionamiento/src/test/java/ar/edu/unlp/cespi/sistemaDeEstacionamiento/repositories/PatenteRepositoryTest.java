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

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.CuentaCorrienteRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.EstacionamientoRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.PatenteRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Estacionamiento;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;

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
		automovilista.setCuentaCorriente(this.cuentaCorrienteRepository.save(new CuentaCorriente(1000d)));
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

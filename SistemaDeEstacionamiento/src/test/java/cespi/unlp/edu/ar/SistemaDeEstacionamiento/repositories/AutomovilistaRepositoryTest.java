package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;


import static org.assertj.core.api.Assertions.assertThat;

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
class AutomovilistaRepositoryTest {

	@Autowired
	AutomovilistaRepository automovilsitaRepository;
	@Autowired
	PatenteRepository patenteRepository;
	@Autowired
	CuentaCorrienteRepository cuentaCorrienteRepository;
	@Autowired 
	EstacionamientoRepository estacionamientoRepository;
	
	Automovilista automovilista;
	Patente patente;
	Estacionamiento estacionamiento;
	
	@BeforeEach
	void setUp() {
		automovilista = new Automovilista("0001112222", "1234");
		automovilista.setCuentaCorriente(this.cuentaCorrienteRepository.save(new CuentaCorriente("01234567890123456", 1000d)));
		patente= this.patenteRepository.save(new Patente("AAA999"));
		automovilista.addPatente(patente);
		estacionamiento=new Estacionamiento(automovilista, patente, LocalDateTime.now());
	}
	
	@Test
	void debeEncontrarAlAutomovilsitaPorEstacionamientoActivoTest() {
		//Given
		estacionamiento = this.estacionamientoRepository.save(estacionamiento);
		automovilista.addEstacionamiento(estacionamiento);
		automovilista= this.automovilsitaRepository.save(automovilista);
		
		//When
		Boolean encontrado = this.automovilsitaRepository.findByIdAndExistingEstacionamientoActivo(automovilista.getId()).isPresent();
		//then
		assertThat(encontrado).isTrue();
	}
	
	@Test
	void noDebeEncontrarAlAutomovilsitaPorEstacionamientoActivoTest() {
		//When
		Boolean encontrado = this.automovilsitaRepository.findByIdAndExistingEstacionamientoActivo(automovilista.getId()).isPresent();
		//then
		assertThat(encontrado).isFalse();
		
		//and Given
		estacionamiento.finalizarEstacionamiento(10);
		estacionamiento = this.estacionamientoRepository.save(estacionamiento);
		automovilista.addEstacionamiento(estacionamiento);
		automovilista= this.automovilsitaRepository.save(automovilista);
		
		//When
		encontrado = this.automovilsitaRepository.findByIdAndExistingEstacionamientoActivo(automovilista.getId()).isPresent();
		//then
		assertThat(encontrado).isFalse();
		
	}
	
	@AfterEach
	void tearDown() {
		estacionamientoRepository.deleteAll();
		patenteRepository.deleteAll();
		automovilsitaRepository.deleteAll();
		cuentaCorrienteRepository.deleteAll();
	}

}

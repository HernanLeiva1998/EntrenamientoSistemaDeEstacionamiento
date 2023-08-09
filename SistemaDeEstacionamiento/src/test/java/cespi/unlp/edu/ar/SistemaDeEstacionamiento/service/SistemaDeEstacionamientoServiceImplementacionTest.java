package cespi.unlp.edu.ar.SistemaDeEstacionamiento.service;
/*
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.ConfiguracionDelSistemaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.CuentaCorrienteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.EstacionamientoRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.PatenteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SistemaDeEstacionamientoServiceImplementacionTest {
	
	@Mock
	private AutomovilistaRepository automovilistaRepository;
	@Mock
	private PatenteRepository patenteRepository;
	@Mock
	private CuentaCorrienteRepository cuentaCorrienteRepository;
	@Mock 
	private EstacionamientoRepository estacionamientoRepository;
	@Mock 
	private ConfiguracionDelSistemaRepository configuracionDelSistemaRepository;
	
	private SistemaDeEstacionamientoService service;

	Automovilista automovilista;
	Patente patente;
	Estacionamiento estacionamiento;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.service=new SistemaDeEstacionamientoServiceImplementacion();
		automovilista = new Automovilista("0001112222", "1234");
		automovilista.setCuentaCorriente(this.cuentaCorrienteRepository.save(new CuentaCorriente("01234567890123456", 1000d)));
		patente=new Patente("AAA999");
		patente= this.patenteRepository.save(patente);
		automovilista.addPatente(patente);
		estacionamiento=new Estacionamiento(automovilista, patente, LocalDateTime.now());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSistemaDeEstacionamientoServiceImplementacion() {
	}

	@Test
	@Disabled
	void testCrearCuentaCorriente() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCrearAutomovilistaStringStringCuentaCorriente() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCrearAutomovilistaStringStringStringCuentaCorriente() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testExisteAutomovilistaPorEmail() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testExisteAutomovilistaPorTelefono() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testExisteCbu() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCrearEstacionamiento() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testAgregarPatente() {
		fail("Not yet implemented");
	}

	@Test
	void testIniciarEstacionamiento() throws SistemaDeEstacionamientoException {

	}

	@Test
	@Disabled
	void testFinalizarEstacionamiento() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCambiarValorPrecioPorHora() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testVerTodosLosAutomovilistas() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testGetAllAutomovilistas() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testVerPatentesDelAutomovilista() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testAgregarPatenteSegunTelefonoDelAutomovilista() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testConseguirPatente() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testConseguirAutomovilistaPorTelefono() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testConseguirEstacionamientoPorId() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testConsequirConfiguracionDelSistema() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testAutenticar() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testConseguirEstacionamientoActivoPorTelefono() {
		fail("Not yet implemented");
	}

}
*/

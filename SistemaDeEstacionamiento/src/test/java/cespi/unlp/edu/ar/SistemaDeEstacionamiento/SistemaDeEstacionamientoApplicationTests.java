package cespi.unlp.edu.ar.SistemaDeEstacionamiento;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.*;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.CuentaCorrienteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.EstacionamientoRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories.PatenteRepository;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoServiceImplementacion;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.LocalDateTimeProviderTest;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.TimeUnitsManager;
import net.bytebuddy.asm.Advice.This;

@SpringBootTest
//@ContextConfiguration(classes = SistemaDeEstacionamientoServiceImplementacion.class)
@Transactional
@Rollback(true)
class SistemaDeEstacionamientoApplicationTests {

	@Autowired
	SistemaDeEstacionamientoService service;
	TimeUnitsManager timeUnitsManager;
	private LocalDateTime localDateTime30minInicio;
	private LocalDateTime localDateTime30minFin;
	private LocalDateTime localDateTime60minInicio;
	private LocalDateTime localDateTime60minFin;
	private LocalDateTime localDateTime12hrInicio;
	private LocalDateTime localDateTime12hrFin;
	private LocalDateTime localDateTime13hrInicio;
	private LocalDateTime localDateTime13hrFin;
	private LocalDateTime localDateFueraDeHorario;
	private LocalDateTime localDateFueraDeHorario1;
	

	@BeforeEach
	void setUp() {
		//this.service= new SistemaDeEstacionamientoServiceImplementacion(ldtp);
		timeUnitsManager=new TimeUnitsManager();
		this.localDateTime30minInicio = LocalDateTime.now()
			    .withHour(8)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);

		this.localDateTime30minFin = localDateTime30minInicio.plusMinutes(30);
		
		this.localDateTime60minInicio = LocalDateTime.now()
			    .withHour(8)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);

		 this.localDateTime60minFin = this.localDateTime60minInicio.plusMinutes(60);
		 
		 this.localDateTime12hrInicio = LocalDateTime.now()
				.withHour(8)
			    .withMinute(0)
			    .withSecond(0)
			    .withNano(0);

		 this.localDateTime12hrFin = this.localDateTime12hrInicio.plusMinutes(720);
		 
		 this.localDateTime13hrInicio = LocalDateTime.now()
					.withHour(8)
				    .withMinute(0)
				    .withSecond(0)
				    .withNano(0);

		 this.localDateTime13hrFin = this.localDateTime13hrInicio.plusHours(13);
		 
		 this.localDateFueraDeHorario = LocalDateTime.now()
					.withHour(7)
				    .withMinute(59)
				    .withSecond(0)
				    .withNano(0);
		 this.localDateFueraDeHorario1 = LocalDateTime.now()
					.withHour(20)
				    .withMinute(1)
				    .withSecond(0)
				    .withNano(0);
		 
		
	}
	@Test
	void contextLoads() {
	}
	
	@Test
	void testTimeUnitsManager() {
		LocalTime inicio= LocalTime.of(8, 0);
		LocalTime fin= LocalTime.of(8, 1);
		int unidadesDeTiempo = this.timeUnitsManager.calcularUnidadesDeTiempo(inicio, fin);
		assertEquals(1, unidadesDeTiempo);
		
		LocalTime inicio0= LocalTime.of(8, 0);
		LocalTime fin0= LocalTime.of(8, 0);
		int unidadesDeTiempo0 = this.timeUnitsManager.calcularUnidadesDeTiempo(inicio0, fin0);
		assertEquals(1, unidadesDeTiempo0);
		
		LocalTime inicioUnaHora= LocalTime.of(8, 0);
		LocalTime finUnaHora= LocalTime.of(9, 0);
		int unidadesDeTiempo1 = this.timeUnitsManager.calcularUnidadesDeTiempo(inicioUnaHora, finUnaHora);
		assertEquals(1, unidadesDeTiempo1);
		
		LocalTime inicioJornada= LocalTime.of(8, 0);
		LocalTime finJornada= LocalTime.of(20, 0);
		int unidadesDeTiempoJornada = this.timeUnitsManager.calcularUnidadesDeTiempo(inicioJornada, finJornada);
		assertEquals(12, unidadesDeTiempoJornada);
		
		LocalTime inicioJornada1= LocalTime.of(8, 30);
		LocalTime finJornada1= LocalTime.of(20, 0);
		int unidadesDeTiempoJornada1 = this.timeUnitsManager.calcularUnidadesDeTiempo(inicioJornada1, finJornada1);
		assertEquals(12, unidadesDeTiempoJornada1);
		
	}
	

	
	@Test
	void testCrearAutomovilistaYAgregarPatente() throws SistemaDeEstacionamientoException{
		CuentaCorriente cuentaCorriente = this.service.crearCuentaCorriente( "1234567890123456789012", 10000d);
		assertTrue(this.service.existeCbu("1234567890123456789012"));
		
		Automovilista automovilista = this.service.crearAutomovilista("2223334444", "1234", "automovilista@mail.com", cuentaCorriente);
		assertNotNull(automovilista.getId());
		assertEquals("1234567890123456789012", automovilista.getCuentaCorriente().getCbu());
		assertEquals(10000d, automovilista.getCuentaCorriente().getSaldo());
		assertEquals("automovilista@mail.com", automovilista.getEmail());
		assertEquals("2223334444", automovilista.getTelefono());
		
		Automovilista automovilistaEncontrado = this.service.conseguirAutomovilistaPorTelefono("2223334444");
		assertEquals(automovilista.getId(), automovilistaEncontrado.getId());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.conseguirAutomovilistaPorTelefono("0000"), "No existe el automovilista");
		
		
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearAutomovilista("2223334444", "1234", cuentaCorriente), "Ya existe una cuenta con este teléfono");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearAutomovilista("2223334444", "1234","automovilista0@mail.com", cuentaCorriente), "Ya existe una cuenta con este teléfono");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearAutomovilista("2223333333", "1234","automovilista@mail.com", cuentaCorriente), "Ya existe una cuenta con este correo electrónico");
		
		
		Patente patente= this.service.agregarPatente(automovilista, "aaa111");
		assertNotNull(patente);
		assertEquals("aaa111", patente.getPatente());
		Patente patente0= this.service.agregarPatente(automovilista, "aa111aa");
		assertEquals("aa111aa", patente0.getPatente());
		
		Patente patenteConseguida=this.service.conseguirPatente("aaa111");
		assertEquals(patente.getPatente(), patenteConseguida.getPatente());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.conseguirPatente("aaa333"), "No existe la patente");
		
		Automovilista automovilistaConPatente= this.service.agregarPatenteSegunTelefonoDelAutomovilista("2223334444", "aaa333");
		assertEquals("aaa333", automovilistaConPatente.getPatentes().get(2).getPatente());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.agregarPatenteSegunTelefonoDelAutomovilista("0000", "aaa333"), "El automovilista no existe");
		
		
		Automovilista automovilista1 = this.service.crearAutomovilista("2213334443", "1234", cuentaCorriente);
		Patente patente2= this.service.agregarPatente(automovilista1, "aaa111");
		assertNotNull(patente2);
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.agregarPatente(automovilista1, "54wq24"), "El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.agregarPatente(automovilista1, "AAA111"), "Esta patente ya esta agregada a la lista de patentes");
		
		CuentaCorriente cuentaCorriente2 = this.service.crearCuentaCorriente( "00000000000000001", 10000d);
	}
	
	@Test
	void testIniciarYFinalizarEstacionamiento() throws SistemaDeEstacionamientoException {
		
		this.service.changeLocalDateTimeProvider(new LocalDateTimeProviderTest(this.localDateTime30minInicio));
		
		
		ConfiguracionDelSistema configuracionDelSistema = service.cambiarValorPrecioPorHora(10d);
		CuentaCorriente cuentaCorriente = this.service.crearCuentaCorriente( "1234567800003456789012", 10000d);
		Automovilista automovilista = this.service.crearAutomovilista("4443334444", "1234", cuentaCorriente);
		Patente patente= this.service.agregarPatente(automovilista, "aaa111");
		Estacionamiento estacionamiento= this.service.iniciarEstacionamiento(automovilista, patente);
		assertNotNull(estacionamiento);
		assertEquals("aaa111", estacionamiento.getPatente().getPatente());
		assertEquals("4443334444", estacionamiento.getAutomovilista().getTelefono());
		
		Estacionamiento estacionamientoActivo = this.service.conseguirEstacionamientoActivoPorTelefono("4443334444");		
		assertEquals(estacionamiento.getId(), estacionamientoActivo.getId());
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.iniciarEstacionamiento(automovilista, patente), "Ya posee un estacionamiento activo");
		
		CuentaCorriente cuentaCorriente2 = this.service.crearCuentaCorriente( "1234567890123456789010", 10000d);
		Automovilista automovilista2 = this.service.crearAutomovilista("0001112222", "1234", cuentaCorriente2);
		this.service.agregarPatente(automovilista2, "aaa111");
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.iniciarEstacionamiento(automovilista2, patente), "La patente ya posee un estacionamiento activo");
		
		CuentaCorriente cuentaCorriente3 = this.service.crearCuentaCorriente( "1234567890123456789000", 1d);
		Automovilista automovilista3 = this.service.crearAutomovilista("3331112222", "1234", cuentaCorriente3);
		Patente patente2 = this.service.agregarPatente(automovilista3, "aaa222");
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.iniciarEstacionamiento(automovilista3, patente2), "No posee suficiente saldo para iniciar el estacionamiento");
		
		
		estacionamiento= this.service.finalizarEstacionamiento(estacionamiento, configuracionDelSistema.getPrecioPorHora());
		assertEquals(10d, estacionamiento.getMonto());
		assertFalse(estacionamiento.getEstaActivo());
		assertEquals(9990, estacionamiento.getAutomovilista().getCuentaCorriente().getSaldo());
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.conseguirEstacionamientoActivoPorTelefono("4443334444"), "No tiene estacionamiento activo");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.conseguirEstacionamientoActivoPorTelefono("1232131232"), "No existe el automovilista");
		
		Estacionamiento estacionamientoPorId = this.service.conseguirEstacionamientoPorId(estacionamiento.getId());
		assertEquals(estacionamiento.getId(), estacionamientoPorId.getId());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.conseguirEstacionamientoPorId(-1l), "No existe el estacionamiento");
		
		
		Estacionamiento r1=service.crearEstacionamiento(localDateTime30minInicio, localDateTime30minFin, automovilista, patente);
		assertEquals(10d, r1.getMonto());
		Estacionamiento r2=service.crearEstacionamiento(localDateTime60minInicio, localDateTime60minFin, automovilista, patente);
		assertEquals(10d, r2.getMonto());
		Estacionamiento r3=service.crearEstacionamiento(localDateTime12hrInicio, localDateTime12hrFin, automovilista, patente);
		assertEquals(120d, r3.getMonto());
		Estacionamiento r4=service.crearEstacionamiento(localDateTime13hrInicio, localDateTime13hrFin, automovilista, patente);
		assertEquals(120d, r4.getMonto());
		assertEquals(9730d, r4.getAutomovilista().getCuentaCorriente().getSaldo());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearEstacionamiento(this.localDateFueraDeHorario, localDateTime30minFin, automovilista, patente), "No es horario activo");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearEstacionamiento(this.localDateFueraDeHorario1, localDateTime30minFin, automovilista, patente), "No es horario activo");
		
		CuentaCorriente cuentaCorriente1 = this.service.crearCuentaCorriente( "1234567890123456789013", 9d);
		Automovilista automovilista1 = this.service.crearAutomovilista("2113334444", "1234", cuentaCorriente1);
		Patente patente1= this.service.agregarPatente(automovilista, "aaa112");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearEstacionamiento(this.localDateTime30minInicio, localDateTime30minFin, automovilista1, patente1), "No posee suficiente saldo para iniciar el estacionamiento");
	}
	
	@Test
	void testAutenticar() throws SistemaDeEstacionamientoException {
		CuentaCorriente cuentaCorriente = this.service.crearCuentaCorriente( "1234567890123456789012", 10000d);
		Automovilista automovilista = this.service.crearAutomovilista("2223334444", "1234", "automovilista@mail.com", cuentaCorriente);
		
		assertEquals(automovilista.getId(), this.service.autenticar("2223334444", "1234").getId());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.autenticar("22233344432432", "1234"), "Error en el teléfono o contraseña");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.autenticar("2223334444", "12345"), "Error en el teléfono o contraseña");
	}
	
	
	
	
	

}

package ar.edu.unlp.cespi.sistemaDeEstacionamiento;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Estacionamiento;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.EstacionamientoService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.PatenteService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalDateTimeProviderTest;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.TimeUnitsManager;

@SpringBootTest
//@ContextConfiguration(classes = SistemaDeEstacionamientoServiceImplementacion.class)
@Transactional
@Rollback(true)
class SistemaDeEstacionamientoApplicationTests {


	@Autowired
	AutomovilistaService automovilistaService;
	@Autowired
    private PatenteService patenteService;
	@Autowired
	private EstacionamientoService estacionamientoService;
    @Autowired 
    private ConfiguracionDelSistemaService configuracionDelSistemaService;
    @Autowired
    private LoginService loginService;
	
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
		CuentaCorriente cuentaCorriente = this.automovilistaService.crearCuentaCorriente( "1234567890123456789012", 10000d);
		assertTrue(this.automovilistaService.existeCbu("1234567890123456789012"));
		
		Automovilista automovilista = this.automovilistaService.crearAutomovilista("2223334444", "1234", "automovilista@mail.com", cuentaCorriente);
		assertNotNull(automovilista.getId());
		assertEquals("1234567890123456789012", automovilista.getCuentaCorriente().getCbu());
		assertEquals(10000d, automovilista.getCuentaCorriente().getSaldo());
		assertEquals("automovilista@mail.com", automovilista.getEmail());
		assertEquals("2223334444", automovilista.getTelefono());
		
		Automovilista automovilistaEncontrado = this.automovilistaService.conseguirAutomovilistaPorTelefono("2223334444");
		assertEquals(automovilista.getId(), automovilistaEncontrado.getId());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.automovilistaService.conseguirAutomovilistaPorTelefono("0000"), "No existe el automovilista");
		
		
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.automovilistaService.crearAutomovilista("2223334444", "1234", cuentaCorriente), "Ya existe una cuenta con este teléfono");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.automovilistaService.crearAutomovilista("2223334444", "1234","automovilista0@mail.com", cuentaCorriente), "Ya existe una cuenta con este teléfono");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.automovilistaService.crearAutomovilista("2223333333", "1234","automovilista@mail.com", cuentaCorriente), "Ya existe una cuenta con este correo electrónico");
		
		
		Patente patente= this.patenteService.agregarPatente(automovilista, "aaa111");
		assertNotNull(patente);
		assertEquals("AAA111", patente.getPatente());
		Patente patente0= this.patenteService.agregarPatente(automovilista, "aa111aa");
		assertEquals("AA111AA", patente0.getPatente());
		
		Patente patenteConseguida=this.patenteService.conseguirPatente("AAA111");
		assertEquals(patente.getPatente(), patenteConseguida.getPatente());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.patenteService.conseguirPatente("aaa333"), "No existe la patente");
		
		Automovilista automovilistaConPatente= this.patenteService.agregarPatenteSegunTelefonoDelAutomovilista("2223334444", "aaa333");
		assertEquals("AAA333", automovilistaConPatente.getPatentes().get(2).getPatente());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.patenteService.agregarPatenteSegunTelefonoDelAutomovilista("0000", "aaa333"), "El automovilista no existe");
		
		
		Automovilista automovilista1 = this.automovilistaService.crearAutomovilista("2213334443", "1234", cuentaCorriente);
		Patente patente2= this.patenteService.agregarPatente(automovilista1, "aaa111");
		assertNotNull(patente2);
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.patenteService.agregarPatente(automovilista1, "54wq24"), "El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.patenteService.agregarPatente(automovilista1, "AAA111"), "Esta patente ya esta agregada a la lista de patentes");
		
		CuentaCorriente cuentaCorriente2 = this.automovilistaService.crearCuentaCorriente( "00000000000000001", 10000d);
	}
	
	@Test
	void testIniciarYFinalizarEstacionamiento() throws SistemaDeEstacionamientoException {
		
		this.estacionamientoService.changeLocalDateTimeProvider(new LocalDateTimeProviderTest(this.localDateTime30minInicio));
		
		
		ConfiguracionDelSistema configuracionDelSistema = configuracionDelSistemaService.cambiarValorPrecioPorHora(10d);
		CuentaCorriente cuentaCorriente = this.automovilistaService.crearCuentaCorriente( "1234567800003456789012", 10000d);
		Automovilista automovilista = this.automovilistaService.crearAutomovilista("4443334444", "1234", cuentaCorriente);
		Patente patente= this.patenteService.agregarPatente(automovilista, "aaa111");
		Estacionamiento estacionamiento= this.estacionamientoService.iniciarEstacionamiento(automovilista, patente);
		assertNotNull(estacionamiento);
		assertEquals("AAA111", estacionamiento.getPatente().getPatente());
		assertEquals("4443334444", estacionamiento.getAutomovilista().getTelefono());
		
		Estacionamiento estacionamientoActivo = this.estacionamientoService.conseguirEstacionamientoActivoPorTelefono("4443334444");		
		assertEquals(estacionamiento.getId(), estacionamientoActivo.getId());
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.iniciarEstacionamiento(automovilista, patente), "Ya posee un estacionamiento activo");
		
		CuentaCorriente cuentaCorriente2 = this.automovilistaService.crearCuentaCorriente( "1234567890123456789010", 10000d);
		Automovilista automovilista2 = this.automovilistaService.crearAutomovilista("0001112222", "1234", cuentaCorriente2);
		this.patenteService.agregarPatente(automovilista2, "aaa111");
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.iniciarEstacionamiento(automovilista2, patente), "La patente ya posee un estacionamiento activo");
		
		CuentaCorriente cuentaCorriente3 = this.automovilistaService.crearCuentaCorriente( "1234567890123456789000", 1d);
		Automovilista automovilista3 = this.automovilistaService.crearAutomovilista("3331112222", "1234", cuentaCorriente3);
		Patente patente2 = this.patenteService.agregarPatente(automovilista3, "aaa222");
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.iniciarEstacionamiento(automovilista3, patente2), "No posee suficiente saldo para iniciar el estacionamiento");
		
		
		estacionamiento= this.estacionamientoService.finalizarEstacionamiento(estacionamiento, configuracionDelSistema.getPrecioPorHora());
		assertEquals(10d, estacionamiento.getMonto());
		assertFalse(estacionamiento.getEstaActivo());
		assertEquals(9990, estacionamiento.getAutomovilista().getCuentaCorriente().getSaldo());
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.conseguirEstacionamientoActivoPorTelefono("4443334444"), "No tiene estacionamiento activo");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.conseguirEstacionamientoActivoPorTelefono("1232131232"), "No existe el automovilista");
		
		Estacionamiento estacionamientoPorId = this.estacionamientoService.conseguirEstacionamientoPorId(estacionamiento.getId());
		assertEquals(estacionamiento.getId(), estacionamientoPorId.getId());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.conseguirEstacionamientoPorId(-1l), "No existe el estacionamiento");
		
		
		Estacionamiento r1=estacionamientoService.crearEstacionamiento(localDateTime30minInicio, localDateTime30minFin, automovilista, patente);
		assertEquals(10d, r1.getMonto());
		Estacionamiento r2=estacionamientoService.crearEstacionamiento(localDateTime60minInicio, localDateTime60minFin, automovilista, patente);
		assertEquals(10d, r2.getMonto());
		Estacionamiento r3=estacionamientoService.crearEstacionamiento(localDateTime12hrInicio, localDateTime12hrFin, automovilista, patente);
		assertEquals(120d, r3.getMonto());
		Estacionamiento r4=estacionamientoService.crearEstacionamiento(localDateTime13hrInicio, localDateTime13hrFin, automovilista, patente);
		assertEquals(120d, r4.getMonto());
		assertEquals(9730d, r4.getAutomovilista().getCuentaCorriente().getSaldo());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.crearEstacionamiento(this.localDateFueraDeHorario, localDateTime30minFin, automovilista, patente), "No es horario activo");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.crearEstacionamiento(this.localDateFueraDeHorario1, localDateTime30minFin, automovilista, patente), "No es horario activo");
		
		CuentaCorriente cuentaCorriente1 = this.automovilistaService.crearCuentaCorriente( "1234567890123456789013", 9d);
		Automovilista automovilista1 = this.automovilistaService.crearAutomovilista("2113334444", "1234", cuentaCorriente1);
		Patente patente1= this.patenteService.agregarPatente(automovilista, "aaa112");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.estacionamientoService.crearEstacionamiento(this.localDateTime30minInicio, localDateTime30minFin, automovilista1, patente1), "No posee suficiente saldo para iniciar el estacionamiento");
	}
	
	@Test
	void testAutenticar() throws SistemaDeEstacionamientoException {
		CuentaCorriente cuentaCorriente = this.automovilistaService.crearCuentaCorriente( "1234567890123456789012", 10000d);
		Automovilista automovilista = this.automovilistaService.crearAutomovilista("2223334444", "1234", "automovilista@mail.com", cuentaCorriente);
		
		assertEquals(automovilista.getId(), this.loginService.autenticar("2223334444", "1234").getId());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.loginService.autenticar("22233344432432", "1234"), "Error en el teléfono o contraseña");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.loginService.autenticar("2223334444", "12345"), "Error en el teléfono o contraseña");
	}
	
	
	
	
	

}

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

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ParkingService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LicensePlateService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalDateTimeProviderTest;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.TimeUnitsManager;

@SpringBootTest
//@ContextConfiguration(classes = SistemaDeEstacionamientoServiceImplementacion.class)
@Transactional
@Rollback(true)
class SistemaDeEstacionamientoApplicationTests {


	@Autowired
	DriverService driverService;
	@Autowired
    private LicensePlateService licensePlateService;
	@Autowired
	private ParkingService parkingService;
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
	void testCrearAutomovilistaYAgregarPatente() throws ParkingSystemException{
		Wallet cuentaCorriente = this.driverService.createWallet(10000d);
		Driver automovilista = this.driverService.createDriver("2223334444", "1234", "automovilista@mail.com", cuentaCorriente, Role.USER);
		assertNotNull(automovilista.getId());
		assertEquals(10000d, automovilista.getWallet().getBalance());
		assertEquals("automovilista@mail.com", automovilista.getEmail());
		assertEquals("2223334444", automovilista.getPhone());
		
		Driver automovilistaEncontrado = this.driverService.getDriverByPhone("2223334444");
		assertEquals(automovilista.getId(), automovilistaEncontrado.getId());
		assertThrows(ParkingSystemException.class, () -> this.driverService.getDriverByPhone("0000"), "No existe el automovilista");
		
		
		
		assertThrows(ParkingSystemException.class, () -> this.driverService.createDriver("2223334444", "1234", cuentaCorriente), "Ya existe una cuenta con este teléfono");
		//assertThrows(SistemaDeEstacionamientoException.class, () -> this.automovilistaService.crearAutomovilista("2223334444", "1234","automovilista0@mail.com", cuentaCorriente), "Ya existe una cuenta con este teléfono");
		//assertThrows(SistemaDeEstacionamientoException.class, () -> this.automovilistaService.crearAutomovilista("2223333333", "1234","automovilista@mail.com", cuentaCorriente), "Ya existe una cuenta con este correo electrónico");
		
		
		LicensePlate patente= this.licensePlateService.addLicensePlate(automovilista, "aaa111");
		assertNotNull(patente);
		assertEquals("AAA111", patente.getLicensePlate());
		LicensePlate patente0= this.licensePlateService.addLicensePlate(automovilista, "aa111aa");
		assertEquals("AA111AA", patente0.getLicensePlate());
		
		LicensePlate patenteConseguida=this.licensePlateService.getLicensePlate("AAA111");
		assertEquals(patente.getLicensePlate(), patenteConseguida.getLicensePlate());
		assertThrows(ParkingSystemException.class, () -> this.licensePlateService.getLicensePlate("aaa333"), "No existe la patente");
		
		Driver automovilistaConPatente= this.licensePlateService.addLicensePlateToDriverByPhone("2223334444", "aaa333");
		assertEquals("AAA333", automovilistaConPatente.getPatentes().get(2).getLicensePlate());
		assertThrows(ParkingSystemException.class, () -> this.licensePlateService.addLicensePlateToDriverByPhone("0000", "aaa333"), "El automovilista no existe");
		
		
		Driver automovilista1 = this.driverService.createDriver("2213334443", "1234", cuentaCorriente);
		LicensePlate patente2= this.licensePlateService.addLicensePlate(automovilista1, "aaa111");
		assertNotNull(patente2);
		
		assertThrows(ParkingSystemException.class, () -> this.licensePlateService.addLicensePlate(automovilista1, "54wq24"), "El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		assertThrows(ParkingSystemException.class, () -> this.licensePlateService.addLicensePlate(automovilista1, "AAA111"), "Esta patente ya esta agregada a la lista de patentes");
	}
	
	@Test
	void testIniciarYFinalizarEstacionamiento() throws ParkingSystemException {
		
		this.parkingService.changeLocalDateTimeProvider(new LocalDateTimeProviderTest(this.localDateTime30minInicio));
		
		

		SystemConfig configuracionDelSistema = configuracionDelSistemaService.cambiarValorPrecioPorHora(10d);
		Wallet cuentaCorriente = this.driverService.createWallet( 10000d);
		Driver automovilista = this.driverService.createDriver("4443334444", "1234", cuentaCorriente);
		LicensePlate patente= this.licensePlateService.addLicensePlate(automovilista, "aaa111");
		Parking estacionamiento= this.parkingService.startParking(automovilista, patente);

		assertNotNull(estacionamiento);
		assertEquals("AAA111", estacionamiento.getLicensePlate().getLicensePlate());
		assertEquals("4443334444", estacionamiento.getDriver().getPhone());
		
		Parking estacionamientoActivo = this.parkingService.getActiveParkingByPhone("4443334444");		
		assertEquals(estacionamiento.getId(), estacionamientoActivo.getId());
		
		assertThrows(ParkingSystemException.class, () -> this.parkingService.startParking(automovilista, patente), "Ya posee un estacionamiento activo");
		
		Wallet cuentaCorriente2 = this.driverService.createWallet( 10000d);
		Driver automovilista2 = this.driverService.createDriver("0001112222", "1234", cuentaCorriente2);
		this.licensePlateService.addLicensePlate(automovilista2, "aaa111");
		
		assertThrows(ParkingSystemException.class, () -> this.parkingService.startParking(automovilista2, patente), "La patente ya posee un estacionamiento activo");
		
		Wallet cuentaCorriente3 = this.driverService.createWallet( 1d);
		Driver automovilista3 = this.driverService.createDriver("3331112222", "1234", cuentaCorriente3);
		LicensePlate patente2 = this.licensePlateService.addLicensePlate(automovilista3, "aaa222");
		
		assertThrows(ParkingSystemException.class, () -> this.parkingService.startParking(automovilista3, patente2), "No posee suficiente saldo para iniciar el estacionamiento");
		
		
		estacionamiento= this.parkingService.endParking(estacionamiento, configuracionDelSistema.getPrecioPorHora());
		assertEquals(10d, estacionamiento.getTotalCost());
		assertFalse(estacionamiento.isActive());
		assertEquals(9990, estacionamiento.getDriver().getWallet().getBalance());
		
		assertThrows(ParkingSystemException.class, () -> this.parkingService.getActiveParkingByPhone("4443334444"), "No tiene estacionamiento activo");
		assertThrows(ParkingSystemException.class, () -> this.parkingService.getActiveParkingByPhone("1232131232"), "No existe el automovilista");
		
		Parking estacionamientoPorId = this.parkingService.getParkingById(estacionamiento.getId());
		assertEquals(estacionamiento.getId(), estacionamientoPorId.getId());
		assertThrows(ParkingSystemException.class, () -> this.parkingService.getParkingById(-1l), "No existe el estacionamiento");
		
		
		Parking r1=parkingService.createParkingWithStartAndEnd(localDateTime30minInicio, localDateTime30minFin, automovilista, patente);
		assertEquals(10d, r1.getTotalCost());
		Parking r2=parkingService.createParkingWithStartAndEnd(localDateTime60minInicio, localDateTime60minFin, automovilista, patente);
		assertEquals(10d, r2.getTotalCost());
		Parking r3=parkingService.createParkingWithStartAndEnd(localDateTime12hrInicio, localDateTime12hrFin, automovilista, patente);
		assertEquals(120d, r3.getTotalCost());
		Parking r4=parkingService.createParkingWithStartAndEnd(localDateTime13hrInicio, localDateTime13hrFin, automovilista, patente);
		assertEquals(120d, r4.getTotalCost());
		assertEquals(9730d, r4.getDriver().getWallet().getBalance());
		assertThrows(ParkingSystemException.class, () -> this.parkingService.createParkingWithStartAndEnd(this.localDateFueraDeHorario, localDateTime30minFin, automovilista, patente), "No es horario activo");
		assertThrows(ParkingSystemException.class, () -> this.parkingService.createParkingWithStartAndEnd(this.localDateFueraDeHorario1, localDateTime30minFin, automovilista, patente), "No es horario activo");
		
		Wallet cuentaCorriente1 = this.driverService.createWallet(9d);
		Driver automovilista1 = this.driverService.createDriver("2113334444", "1234", cuentaCorriente1);
		LicensePlate patente1= this.licensePlateService.addLicensePlate(automovilista, "aaa112");
		assertThrows(ParkingSystemException.class, () -> this.parkingService.createParkingWithStartAndEnd(this.localDateTime30minInicio, localDateTime30minFin, automovilista1, patente1), "No posee suficiente saldo para iniciar el estacionamiento");
	}
	
	@Test
	void testAutenticar() throws ParkingSystemException {
		Wallet cuentaCorriente = this.driverService.createWallet(10000d);
		Driver automovilista = this.driverService.createDriver("2223334444", "1234", "automovilista@mail.com", cuentaCorriente, Role.USER);
		
		assertEquals(automovilista.getId(), this.loginService.autenticar("2223334444", "1234").getId());
		assertThrows(ParkingSystemException.class, () -> this.loginService.autenticar("22233344432432", "1234"), "Error en el teléfono o contraseña");
		assertThrows(ParkingSystemException.class, () -> this.loginService.autenticar("2223334444", "12345"), "Error en el teléfono o contraseña");
	}
	
	
	
	
	

}

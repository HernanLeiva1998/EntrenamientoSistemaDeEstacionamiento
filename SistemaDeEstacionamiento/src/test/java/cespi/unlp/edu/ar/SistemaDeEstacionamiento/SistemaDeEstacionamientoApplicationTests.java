package cespi.unlp.edu.ar.SistemaDeEstacionamiento;

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

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.*;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.TimeUnitsManager;
import net.bytebuddy.asm.Advice.This;

@SpringBootTest
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
		Automovilista automovilista = this.service.crearAutomovilista("2213334444", "1234", cuentaCorriente);
		assertNotNull(automovilista.getId());
		assertEquals("1234567890123456789012", automovilista.getCuentaCorriente().getCbu());
		assertEquals(10000d, automovilista.getCuentaCorriente().getSaldo());
		assertEquals("2213334444", automovilista.getTelefono());
		
		Patente patente= this.service.agregarPatente(automovilista, "aaa111");
		assertNotNull(patente);
		assertEquals("aaa111", patente.getPatente());
		Patente patente0= this.service.agregarPatente(automovilista, "aa111aa");
		assertEquals("aa111aa", patente0.getPatente());
		
		Automovilista automovilista1 = this.service.crearAutomovilista("2213334443", "1234", cuentaCorriente);
		Patente patente1= this.service.agregarPatente(automovilista1, "aaa111");
		assertNotNull(patente1);
		
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.agregarPatente(automovilista1, "54wq24"), "El formato de patente no es valido. Debe ser AAA111 o bien AA111AA");
		
	}
	
	@Test
	void testIniciarYFinalizarReserva() throws SistemaDeEstacionamientoException {
		
		ConfiguracionDelSistema configuracionDelSistema = service.cambiarValorPrecioPorHora(10d);
		CuentaCorriente cuentaCorriente = this.service.crearCuentaCorriente( "1234567890123456789012", 10000d);
		Automovilista automovilista = this.service.crearAutomovilista("2213334444", "1234", cuentaCorriente);
		Patente patente= this.service.agregarPatente(automovilista, "aaa111");
		Reserva reserva= this.service.iniciarReserva(automovilista, patente);
		assertNotNull(reserva);
		assertEquals("aaa111", reserva.getPatente().getPatente());
		assertEquals("2213334444", reserva.getAutomovilista().getTelefono());
		
		
		reserva= this.service.finalizarReserva(reserva, configuracionDelSistema.getPrecioPorHora());
		assertEquals(10d, reserva.getMonto());
		assertFalse(reserva.getEstaActiva());
		assertEquals(9990, reserva.getAutomovilista().getCuentaCorriente().getSaldo());
		
		Reserva r1=service.crearReserva(localDateTime30minInicio, localDateTime30minFin, automovilista, patente);
		assertEquals(10d, r1.getMonto());
		Reserva r2=service.crearReserva(localDateTime60minInicio, localDateTime60minFin, automovilista, patente);
		assertEquals(10d, r2.getMonto());
		Reserva r3=service.crearReserva(localDateTime12hrInicio, localDateTime12hrFin, automovilista, patente);
		assertEquals(120d, r3.getMonto());
		Reserva r4=service.crearReserva(localDateTime13hrInicio, localDateTime13hrFin, automovilista, patente);
		assertEquals(120d, r4.getMonto());
		assertEquals(9730d, r4.getAutomovilista().getCuentaCorriente().getSaldo());
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearReserva(this.localDateFueraDeHorario, localDateTime30minFin, automovilista, patente), "No es horario activo");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearReserva(this.localDateFueraDeHorario1, localDateTime30minFin, automovilista, patente), "No es horario activo");
		
		CuentaCorriente cuentaCorriente1 = this.service.crearCuentaCorriente( "1234567890123456789013", 9d);
		Automovilista automovilista1 = this.service.crearAutomovilista("2113334444", "1234", cuentaCorriente1);
		Patente patente1= this.service.agregarPatente(automovilista, "aaa112");
		assertThrows(SistemaDeEstacionamientoException.class, () -> this.service.crearReserva(this.localDateTime30minInicio, localDateTime30minFin, automovilista1, patente1), "No posee suficiente saldo para iniciar el estacionamiento");
		
	}
	
	

}

package cespi.unlp.edu.ar.SistemaDeEstacionamiento.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Reserva;

@Service
public interface SistemaDeEstacionamientoService {
	
	public CuentaCorriente crearCuentaCorriente(String cbu, Double saldo);
	
	public Automovilista crearAutomovilista(String telefono, String contraseña, CuentaCorriente cuentaCorriente);
	
	public Reserva crearReserva(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p);
	
	public Patente agregarPatente(Automovilista automovilista, String patente);
	
	public Reserva iniciarReserva(Automovilista automovilista, Patente patente);

	public Reserva finalizarReserva(Reserva reserva, Double precioPorHora);
	
	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor);
	
}

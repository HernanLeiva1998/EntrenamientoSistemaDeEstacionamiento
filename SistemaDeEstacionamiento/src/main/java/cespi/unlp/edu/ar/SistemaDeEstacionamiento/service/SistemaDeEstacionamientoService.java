package cespi.unlp.edu.ar.SistemaDeEstacionamiento.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@Service
public interface SistemaDeEstacionamientoService {
	
	public CuentaCorriente crearCuentaCorriente(String cbu, Double saldo);
	
	public Automovilista crearAutomovilista(String telefono, String contraseña, CuentaCorriente cuentaCorriente);
	
	public Estacionamiento crearEstacionamiento(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p) throws SistemaDeEstacionamientoException;
	
	public Patente agregarPatente(Automovilista automovilista, String patente) throws SistemaDeEstacionamientoException;
	
	public Estacionamiento iniciarEstacionamiento(Automovilista automovilista, Patente patente) throws SistemaDeEstacionamientoException;

	public Estacionamiento finalizarEstacionamiento(Estacionamiento estacionamiento, Double precioPorHora) throws SistemaDeEstacionamientoException;
	
	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) throws SistemaDeEstacionamientoException;

	public List<Automovilista> getAllAutomovilistas();
	
	public List<Automovilista> verTodosLosAutomovilistas();

	public List<Patente> verPatentesDelAutomovilista(String telefono) throws SistemaDeEstacionamientoException;

	public Automovilista agregarPatenteSegunTelefonoDelAutomovilista(String telefono, String patente) throws SistemaDeEstacionamientoException;

	public Patente conseguirPatente(String string) throws SistemaDeEstacionamientoException;

	public Automovilista conseguirAutomovilistaPorTelefono(String string) throws SistemaDeEstacionamientoException;

	public Estacionamiento conseguirEstacionamientoPorId(Long id_estacionamiento) throws SistemaDeEstacionamientoException;

	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException;

	public Automovilista autenticar(String telefono, String password) throws SistemaDeEstacionamientoException;

	public Estacionamiento conseguirEstacionamientoActivoPorTelefono(String telefono)  throws SistemaDeEstacionamientoException;;
	
}

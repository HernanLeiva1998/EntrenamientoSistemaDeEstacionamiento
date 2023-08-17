package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;

@Service
public interface AutomovilistaService {

	public Automovilista crearAutomovilista(String telefono, String contraseña, CuentaCorriente cuentaCorriente) throws SistemaDeEstacionamientoException;
	
	public Automovilista crearAutomovilista(String telefono, String contraseña, String email, CuentaCorriente cuentaCorriente) throws SistemaDeEstacionamientoException;
	
	public CuentaCorriente crearCuentaCorriente(String cbu, Double saldo) throws SistemaDeEstacionamientoException;
	
	public boolean existeAutomovilistaPorTelefono(String telefono);
	
	public boolean existeAutomovilistaPorEmail(String email);
	
	public Automovilista conseguirAutomovilistaPorTelefono(String string) throws SistemaDeEstacionamientoException;

	public boolean existeCbu(String cbu);
	
}

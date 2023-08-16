package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;

@Service
public interface PatenteService {

	public Patente agregarPatente(Automovilista automovilista, String patente) throws SistemaDeEstacionamientoException;
	
	public List<Patente> verPatentesDelAutomovilista(String telefono) throws SistemaDeEstacionamientoException;

	public Automovilista agregarPatenteSegunTelefonoDelAutomovilista(String telefono, String patente) throws SistemaDeEstacionamientoException;
	
	public Patente conseguirPatente(String string) throws SistemaDeEstacionamientoException;

}

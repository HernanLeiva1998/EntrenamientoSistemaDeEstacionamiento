package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Estacionamiento;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils.LocalDateTimeProvider;

@Service
public interface EstacionamientoService {
	public Estacionamiento crearEstacionamiento(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p) throws SistemaDeEstacionamientoException;
	
	public Estacionamiento iniciarEstacionamiento(Automovilista automovilista, Patente patente) throws SistemaDeEstacionamientoException;

	public Estacionamiento finalizarEstacionamiento(Estacionamiento estacionamiento, Double precioPorHora) throws SistemaDeEstacionamientoException;
	
	public Estacionamiento conseguirEstacionamientoPorId(Long id_estacionamiento) throws SistemaDeEstacionamientoException;

	public Estacionamiento conseguirEstacionamientoActivoPorTelefono(String telefono)  throws SistemaDeEstacionamientoException;

	void changeLocalDateTimeProvider(LocalDateTimeProvider ldtp);

}

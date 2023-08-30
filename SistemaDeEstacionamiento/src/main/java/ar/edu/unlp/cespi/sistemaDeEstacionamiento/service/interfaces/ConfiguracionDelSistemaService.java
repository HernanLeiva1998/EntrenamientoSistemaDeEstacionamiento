package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

<<<<<<< HEAD
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;
=======
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;
>>>>>>> 46550c38deefa9ea8b4b2c11ae89340a8327d42a

@Service
public interface ConfiguracionDelSistemaService {

<<<<<<< HEAD
	public SystemConfig consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException;

	public SystemConfig cambiarValorPrecioPorHora(Double valor) throws SistemaDeEstacionamientoException;
=======
	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws ParkingSystemException;

	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) throws ParkingSystemException;
>>>>>>> 46550c38deefa9ea8b4b2c11ae89340a8327d42a

}

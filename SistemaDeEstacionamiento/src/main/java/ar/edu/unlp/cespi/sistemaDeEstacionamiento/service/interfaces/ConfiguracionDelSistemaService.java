package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;



@Service
public interface ConfiguracionDelSistemaService {


	public SystemConfig consequirConfiguracionDelSistema() throws ParkingSystemException;

	public SystemConfig cambiarValorPrecioPorHora(Double valor) throws ParkingSystemException;

}

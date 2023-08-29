package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;

@Service
public interface ConfiguracionDelSistemaService {

	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws ParkingSystemException;

	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) throws ParkingSystemException;

}

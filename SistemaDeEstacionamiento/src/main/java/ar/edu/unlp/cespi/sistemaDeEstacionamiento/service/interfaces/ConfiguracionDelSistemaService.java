package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;

@Service
public interface ConfiguracionDelSistemaService {

	public SystemConfig consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException;

	public SystemConfig cambiarValorPrecioPorHora(Double valor) throws SistemaDeEstacionamientoException;

}

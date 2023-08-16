package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;

@Service
public interface ConfiguracionDelSistemaService {

	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException;

	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) throws SistemaDeEstacionamientoException;

}

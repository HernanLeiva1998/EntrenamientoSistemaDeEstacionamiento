package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.ConfiguracionDelSistemaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;

public class ConfiguracionDelSistemaServiceImplementation implements ConfiguracionDelSistemaService {

	
	@Autowired
	ConfiguracionDelSistemaRepository configuracionDelSistemaRepository;
	
	
	@Override
	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException{
		// TODO Auto-generated method stub
		List<ConfiguracionDelSistema> configuracionDelSistemaList= (List<ConfiguracionDelSistema>) this.configuracionDelSistemaRepository.findAll();
		if (!configuracionDelSistemaList.isEmpty()) {
			return configuracionDelSistemaList.get(0);
		}
		return  this.cambiarValorPrecioPorHora(10d);
		
	}

	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) {
		ConfiguracionDelSistema cds = new ConfiguracionDelSistema( valor);
		return configuracionDelSistemaRepository.save(cds);
	}

}

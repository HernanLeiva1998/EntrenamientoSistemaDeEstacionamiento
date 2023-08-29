package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.ConfiguracionDelSistemaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;

public class ConfiguracionDelSistemaServiceImplementation implements ConfiguracionDelSistemaService {

	
	@Autowired
	ConfiguracionDelSistemaRepository configuracionDelSistemaRepository;
	
	
	@Override
	public ConfiguracionDelSistema consequirConfiguracionDelSistema() throws ParkingSystemException{
		// TODO Auto-generated method stub
		List<ConfiguracionDelSistema> configuracionDelSistemaList= (List<ConfiguracionDelSistema>) this.configuracionDelSistemaRepository.findAll();
		if (!configuracionDelSistemaList.isEmpty()) {
			return configuracionDelSistemaList.get(0);
		}
		return  this.configurar(
				10d, 
				LocalTime.of(8, 0),
				LocalTime.of(20, 0), 
				"^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$");
	}
	
	private ConfiguracionDelSistema configurar(Double valor, LocalTime horaInicio, LocalTime horaFin, String formatosDePatentes) {
		ConfiguracionDelSistema cds = new ConfiguracionDelSistema( valor, horaInicio, horaFin, formatosDePatentes );
		return configuracionDelSistemaRepository.save(cds);
	}
	public ConfiguracionDelSistema cambiarValorPrecioPorHora(Double valor) throws ParkingSystemException {
		ConfiguracionDelSistema cds = this.consequirConfiguracionDelSistema();
		cds.setPrecioPorHora(valor);
		return configuracionDelSistemaRepository.save(cds);
	}
	
	public ConfiguracionDelSistema cambiarHorarioActivo(LocalTime horaInicio, LocalTime horaFin) throws ParkingSystemException {
		ConfiguracionDelSistema configuracionDelSistema = this.consequirConfiguracionDelSistema();
		configuracionDelSistema.cambiarHorario(horaInicio, horaFin);
		return this.configuracionDelSistemaRepository.save(configuracionDelSistema);
	}

}

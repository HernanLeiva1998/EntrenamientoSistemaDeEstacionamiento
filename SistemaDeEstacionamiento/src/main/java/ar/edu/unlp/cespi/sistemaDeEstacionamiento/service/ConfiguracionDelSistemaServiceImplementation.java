package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.ConfiguracionDelSistemaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;

public class ConfiguracionDelSistemaServiceImplementation implements ConfiguracionDelSistemaService {

	
	@Autowired
	ConfiguracionDelSistemaRepository configuracionDelSistemaRepository;
	
	
	@Override
	public SystemConfig consequirConfiguracionDelSistema() throws SistemaDeEstacionamientoException{
		// TODO Auto-generated method stub
		List<SystemConfig> configuracionDelSistemaList= (List<SystemConfig>) this.configuracionDelSistemaRepository.findAll();
		if (!configuracionDelSistemaList.isEmpty()) {
			return configuracionDelSistemaList.get(0);
		}
		return  this.configurar(
				10d, 
				LocalTime.of(8, 0),
				LocalTime.of(20, 0), 
				"^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$");
	}
	
	private SystemConfig configurar(Double valor, LocalTime horaInicio, LocalTime horaFin, String formatosDePatentes) {
		SystemConfig cds = new SystemConfig( valor, horaInicio, horaFin, formatosDePatentes );
		return configuracionDelSistemaRepository.save(cds);
	}
	public SystemConfig cambiarValorPrecioPorHora(Double valor) throws SistemaDeEstacionamientoException {
		SystemConfig cds = this.consequirConfiguracionDelSistema();
		cds.setPrecioPorHora(valor);
		return configuracionDelSistemaRepository.save(cds);
	}
	
	public SystemConfig cambiarHorarioActivo(LocalTime horaInicio, LocalTime horaFin) throws SistemaDeEstacionamientoException {
		SystemConfig configuracionDelSistema = this.consequirConfiguracionDelSistema();
		configuracionDelSistema.cambiarHorario(horaInicio, horaFin);
		return this.configuracionDelSistemaRepository.save(configuracionDelSistema);
	}

}

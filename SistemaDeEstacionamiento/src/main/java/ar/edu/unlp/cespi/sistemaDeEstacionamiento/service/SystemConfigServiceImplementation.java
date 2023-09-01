package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.SystemConfigRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.SystemConfigService;

public class SystemConfigServiceImplementation implements SystemConfigService {

	
	@Autowired
	SystemConfigRepository configuracionDelSistemaRepository;
	
	
	@Override
	public SystemConfig getSystemConfig() throws ParkingSystemException{
		// TODO Auto-generated method stub
		List<SystemConfig> configuracionDelSistemaList= (List<SystemConfig>) this.configuracionDelSistemaRepository.findAll();
		if (!configuracionDelSistemaList.isEmpty()) {
			return configuracionDelSistemaList.get(0);
		}
		return  this.configure(
				10d, 
				LocalTime.of(8, 0),
				LocalTime.of(20, 0), 
				"^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$");
	}
	
	private SystemConfig configure(Double valor, LocalTime horaInicio, LocalTime horaFin, String formatosDePatentes) {
		SystemConfig cds = new SystemConfig( valor, horaInicio, horaFin, formatosDePatentes );
		return configuracionDelSistemaRepository.save(cds);
	}
	public SystemConfig changePricePerHour(Double valor) throws ParkingSystemException {
		SystemConfig cds = this.getSystemConfig();
		cds.setPrecioPorHora(valor);
		return configuracionDelSistemaRepository.save(cds);
	}
	
	public SystemConfig changeActiveTime(LocalTime horaInicio, LocalTime horaFin) throws ParkingSystemException {
		SystemConfig configuracionDelSistema = this.getSystemConfig();
		configuracionDelSistema.cambiarHorario(horaInicio, horaFin);
		return this.configuracionDelSistemaRepository.save(configuracionDelSistema);
	}

}

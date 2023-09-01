package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;



@Service
public interface SystemConfigService {


	public SystemConfig getSystemConfig() throws ParkingSystemException;

	public SystemConfig changePricePerHour(Double valor) throws ParkingSystemException;

}

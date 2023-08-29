package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.DriverServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.ConfiguracionDelSistemaServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.ParkingServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.LoginServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.LicensePlateServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ParkingService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LicensePlateService;

@Configuration
public class SpringDataConfiguration {

	@Bean
    public DriverService springDataJpaAutomovilistaService() {
		return new DriverServiceImplementation();
	}
	
	@Bean
    public ParkingService springDataJpaEstacionamientoService() {
		return new ParkingServiceImplementation();
	}
	
	@Bean
    public LoginService springDataJpaLoginService() {
		return new LoginServiceImplementation();
	}
	
	@Bean
    public LicensePlateService springDataJpaPatenteService() {
		return new LicensePlateServiceImplementation();
	}
	
	@Bean
	public ConfiguracionDelSistemaService springDataJpaConfiguracionDelSistemaService() {
		return new ConfiguracionDelSistemaServiceImplementation();
	}

}

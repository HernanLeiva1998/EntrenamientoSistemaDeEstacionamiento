package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.AutomovilistaServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.EstacionamientoServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.LoginServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.PatenteServiceImplementation;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoServiceImplementacion;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.EstacionamientoService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.LoginService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.PatenteService;

@Configuration
public class SpringDataConfiguration {
	@Bean
    public SistemaDeEstacionamientoService springDataJpaService() {
        return new SistemaDeEstacionamientoServiceImplementacion();
    }
	
	@Bean
    public AutomovilistaService springDataJpaAutomovilistaService() {
		return new AutomovilistaServiceImplementation();
	}
	
	@Bean
    public EstacionamientoService springDataJpaEstacionamientoService() {
		return new EstacionamientoServiceImplementation();
	}
	
	@Bean
    public LoginService springDataJpaLoginService() {
		return new LoginServiceImplementation();
	}
	
	@Bean
    public PatenteService springDataJpaPatenteService() {
		return new PatenteServiceImplementation();
	}

}

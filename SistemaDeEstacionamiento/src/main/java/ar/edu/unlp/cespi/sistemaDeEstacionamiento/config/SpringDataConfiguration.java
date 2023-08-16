package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoServiceImplementacion;

@Configuration
public class SpringDataConfiguration {
	@Bean
    public SistemaDeEstacionamientoService springDataJpaService() {
        return new SistemaDeEstacionamientoServiceImplementacion();
    }

}

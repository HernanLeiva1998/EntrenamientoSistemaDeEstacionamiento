package cespi.unlp.edu.ar.SistemaDeEstacionamiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoServiceImplementacion;

@Configuration
public class SpringDataConfiguration {
	@Bean
    public SistemaDeEstacionamientoService springDataJpaService() {
        return new SistemaDeEstacionamientoServiceImplementacion();
    }

}

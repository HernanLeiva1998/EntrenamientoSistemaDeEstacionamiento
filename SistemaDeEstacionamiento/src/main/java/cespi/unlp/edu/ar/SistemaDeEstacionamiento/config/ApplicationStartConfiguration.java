package cespi.unlp.edu.ar.SistemaDeEstacionamiento.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils.SistemaDeEstacionamientoException;

@Configuration
public class ApplicationStartConfiguration implements ApplicationRunner {
	
	@Autowired
	SistemaDeEstacionamientoService service;
	
	@Override
	public void run(ApplicationArguments args) throws SistemaDeEstacionamientoException {
		this.service.consequirConfiguracionDelSistema();
	}

}

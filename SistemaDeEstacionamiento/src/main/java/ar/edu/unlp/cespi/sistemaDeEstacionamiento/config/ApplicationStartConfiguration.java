package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;

@Configuration
public class ApplicationStartConfiguration implements ApplicationRunner {
	
	@Autowired
	AutomovilistaService automovilistaService;
    @Autowired 
    private ConfiguracionDelSistemaService configuracionDelSistemaService;
	
	@Override
	public void run(ApplicationArguments args) throws SistemaDeEstacionamientoException {
		this.configuracionDelSistemaService.consequirConfiguracionDelSistema();
		if (!this.automovilistaService.existeAutomovilistaPorTelefono("2213334444") )
		this.automovilistaService.crearAutomovilista(
				"2213334444",
				"1234",
				"user@email.com",
				automovilistaService.crearCuentaCorriente(String.valueOf("123456789012"), 1000d));
		if (!this.automovilistaService.existeAutomovilistaPorTelefono("1112223333"))
		this.automovilistaService.crearAutomovilista(
				"1112223333",
				"1234",
				"user0@email.com",
				automovilistaService.crearCuentaCorriente(String.valueOf("111111111111"), 1d));	
	}

}

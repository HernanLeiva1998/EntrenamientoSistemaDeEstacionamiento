package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.ConfiguracionDelSistemaService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationStartConfiguration implements ApplicationRunner {
	
	@Autowired
	AutomovilistaService automovilistaService;
    @Autowired 
    private ConfiguracionDelSistemaService configuracionDelSistemaService;

    private final PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws SistemaDeEstacionamientoException {
		this.configuracionDelSistemaService.consequirConfiguracionDelSistema();
		if (!this.automovilistaService.existeAutomovilistaPorTelefono("2213334444") )
		this.automovilistaService.crearAutomovilista(
				"2213334444",
				passwordEncoder.encode("1234"),
				"user@email.com",
				automovilistaService.crearCuentaCorriente(1000d));
		if (!this.automovilistaService.existeAutomovilistaPorTelefono("1112223333"))
		this.automovilistaService.crearAutomovilista(
				"1112223333",
				passwordEncoder.encode("1234"),
				"user0@email.com",
				automovilistaService.crearCuentaCorriente(1d));	
	}

}

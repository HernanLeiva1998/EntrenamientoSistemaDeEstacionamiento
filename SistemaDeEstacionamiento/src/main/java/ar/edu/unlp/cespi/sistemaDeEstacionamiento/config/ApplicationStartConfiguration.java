package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

@Configuration
public class ApplicationStartConfiguration implements ApplicationRunner {
	
	@Autowired
	SistemaDeEstacionamientoService service;
	
	@Override
	public void run(ApplicationArguments args) throws SistemaDeEstacionamientoException {
		this.service.consequirConfiguracionDelSistema();
		if (!this.service.existeAutomovilistaPorTelefono("2213334444") )
		this.service.crearAutomovilista(
				"2213334444",
				"1234",
				"user@email.com",
				service.crearCuentaCorriente(String.valueOf("123456789012"), 1000d));
		if (!this.service.existeAutomovilistaPorTelefono("1112223333"))
		this.service.crearAutomovilista(
				"1112223333",
				"1234",
				"user0@email.com",
				service.crearCuentaCorriente(String.valueOf("111111111111"), 1d));	
	}

}

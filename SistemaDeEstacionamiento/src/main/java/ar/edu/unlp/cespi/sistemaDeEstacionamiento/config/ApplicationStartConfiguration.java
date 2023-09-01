package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.SystemConfigService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationStartConfiguration implements ApplicationRunner {
	
	@Autowired
	DriverService driverService;
    @Autowired 
    private SystemConfigService configuracionDelSistemaService;

    private final PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws ParkingSystemException {
		this.configuracionDelSistemaService.getSystemConfig();
		if (!this.driverService.checkDriverExistsByPhone("2213334444") )
		this.driverService.createDriver(
				"2213334444",
				passwordEncoder.encode("1234"),
				"user@email.com",
				driverService.createWallet(1000d),
				Role.USER);
		if (!this.driverService.checkDriverExistsByPhone("1112223333"))
		this.driverService.createDriver(
				"1112223333",
				passwordEncoder.encode("1234"),
				"user0@email.com",
				driverService.createWallet(1d),
				Role.USER);	
	}

}

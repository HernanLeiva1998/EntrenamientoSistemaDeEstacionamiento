package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.WalletRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;

public class DriverServiceImplementation implements DriverService{


	@Autowired
	DriverRepository driverRepository;
	@Autowired
	WalletRepository walletRepository;
	
	@Transactional
	public Wallet createWallet(Double balance) throws ParkingSystemException {
		
			new Wallet(balance);
			return this.walletRepository.save(new Wallet(balance));
		
		
	}
	
	@Override
	@Transactional
	public Driver createDriver(String phone, String password, Wallet wallet) 
			throws ParkingSystemException{
		try {
			if (phone.length() != 10) {
				throw new ParkingSystemException("El telefono debe contener 10 números");	
			}
			if (!phone.matches("\\d+")) {
				throw new ParkingSystemException("El telefono solo puede contener caracteres númericos");	
			}		
			if(password.length()<4) {
				throw new ParkingSystemException("La contraseña debe tener un largo igual o mayor a 4 carácteres");	
			}
			if (this.checkDriverExistsByPhone(phone)) {
				throw new ParkingSystemException("Ya existe una cuenta con este teléfono");
			}
			Driver driver= new Driver(phone, password);
			driver.setWallet(wallet);
			return this.driverRepository.save(driver);
			
		} catch (ParkingSystemException e) {
			throw e;
		}
		
		
		
	}
	
	@Override
	@Transactional
	public Driver createDriver(String phone, String password, String email, Wallet wallet, Role role)
			throws ParkingSystemException{
		try {
			if (phone.length() != 10) {
				throw new ParkingSystemException("El telefono debe contener 10 números");	
			}
			if (!phone.matches("\\d+")) {
				throw new ParkingSystemException("El telefono solo puede contener caracteres númericos");	
			}		
			if (this.checkDriverExistsByPhone(phone)) {
				throw new ParkingSystemException("Ya existe una cuenta con este teléfono");
			}else if (this.checkDriverExistsByEmail(email)) {
				throw new ParkingSystemException("Ya existe una cuenta con este correo electrónico");
			}
			walletRepository.save(wallet);
			Driver driver= new Driver(phone, password, email, role);
			driver.setWallet(wallet);
			return this.driverRepository.save(driver);
			
		} catch (ParkingSystemException e) {
			throw e;
		}
	}
	
	public boolean checkDriverExistsByEmail(String email) {
		return this.driverRepository.findByEmail(email).isPresent();
	}
	
	public boolean checkDriverExistsByPhone(String phone) {
		return this.driverRepository.findByPhone(phone).isPresent();
	}
	
	@Override
	public boolean walletExists(String accountNumber) {
		return this.walletRepository.findByAccountNumber(accountNumber).isPresent();
	}

	@Override
	public Driver getDriverByPhone(String phone) throws ParkingSystemException {
		Optional<Driver> driverOptional = this.driverRepository.findByPhone(phone);
		if (driverOptional.isPresent()) {
			return driverOptional.get();
		}
		throw new ParkingSystemException("No existe el automovilista");
	}

}

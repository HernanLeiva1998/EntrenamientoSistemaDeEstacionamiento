package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces;

import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;

@Service
public interface DriverService {

	public Driver createDriver(String phone, String contrasena, Wallet wallet) throws ParkingSystemException;
	
	public Driver createDriver(String phone, String contrasena, String email, Wallet wallet, Role role) throws ParkingSystemException;
	
	public Wallet createWallet(Double saldo) throws ParkingSystemException;
	
	public boolean checkDriverExistsByPhone(String phone);
	
	public boolean checkDriverExistsByEmail(String email);
	
	public Driver getDriverByPhone(String phone) throws ParkingSystemException;

	public boolean walletExists(String accountNumber);
	
}

package ar.edu.unlp.cespi.sistemaDeEstacionamiento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.SistemaDeEstacionamientoException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Automovilista;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.AutomovilistaRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.CuentaCorrienteRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.AutomovilistaService;

public class AutomovilistaServiceImplementation implements AutomovilistaService{


	@Autowired
	AutomovilistaRepository automovilistaRepository;
	@Autowired
	CuentaCorrienteRepository cuentaCorrienteRepository;
	
	@Transactional
	public CuentaCorriente crearCuentaCorriente(String cbu, Double saldo) throws SistemaDeEstacionamientoException {
		try {
			CuentaCorriente cuentaCorriente = new CuentaCorriente(cbu, saldo);
			return this.cuentaCorrienteRepository.save(cuentaCorriente);
		} catch (Exception e) {
			throw new SistemaDeEstacionamientoException(e.getMessage());
		}
		
	}
	
	@Override
	@Transactional
	public Automovilista crearAutomovilista(String telefono, String contraseña, CuentaCorriente cuentaCorriente) 
			throws SistemaDeEstacionamientoException{
		try {
			if (this.existeAutomovilistaPorTelefono(telefono)) {
				throw new SistemaDeEstacionamientoException("Ya existe una cuenta con este teléfono");
			}
			Automovilista automovilista= new Automovilista(telefono, contraseña);
			automovilista.setCuentaCorriente(cuentaCorriente);
			return this.automovilistaRepository.save(automovilista);
			
		} catch (SistemaDeEstacionamientoException e) {
			throw e;
		}
		
		
		
	}
	
	@Override
	@Transactional
	public Automovilista crearAutomovilista(String telefono, String contraseña, String email, CuentaCorriente cuentaCorriente)
			throws SistemaDeEstacionamientoException{
		try {
			if (this.existeAutomovilistaPorTelefono(telefono)) {
				throw new SistemaDeEstacionamientoException("Ya existe una cuenta con este teléfono");
			}else if (this.existeAutomovilistaPorEmail(email)) {
				throw new SistemaDeEstacionamientoException("Ya existe una cuenta con este correo electrónico");
			}
			Automovilista automovilista= new Automovilista(telefono, contraseña, email);
			automovilista.setCuentaCorriente(cuentaCorriente);
			return this.automovilistaRepository.save(automovilista);
			
		} catch (SistemaDeEstacionamientoException e) {
			throw e;
		}
	}
	
	public boolean existeAutomovilistaPorEmail(String email) {
		return this.automovilistaRepository.findByEmail(email).isPresent();
	}
	
	public boolean existeAutomovilistaPorTelefono(String telefono) {
		return this.automovilistaRepository.findByTelefono(telefono).isPresent();
	}
	
	@Override
	public boolean existeCbu(String cbu) {
		return this.cuentaCorrienteRepository.findByCbu(cbu).isPresent();
	}

	@Override
	public Automovilista conseguirAutomovilistaPorTelefono(String telefono) throws SistemaDeEstacionamientoException {
		Optional<Automovilista> automovilistaOptional = this.automovilistaRepository.findByTelefono(telefono);
		if (automovilistaOptional.isPresent()) {
			return automovilistaOptional.get();
		}
		throw new SistemaDeEstacionamientoException("No existe el automovilista");
	}

}

package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.time.LocalDateTime;

public class LocalDateTimeProviderImplementation implements LocalDateTimeProvider {

	@Override
	public LocalDateTime now() {
		// TODO Auto-generated method stub
		return LocalDateTime.now();
	}

}

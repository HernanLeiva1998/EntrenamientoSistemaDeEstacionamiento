package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.time.LocalDateTime;


public class LocalDateTimeProviderTest implements LocalDateTimeProvider {

	private LocalDateTime testDateTime;
	
	public LocalDateTimeProviderTest(LocalDateTime testDateTime) {
		this.testDateTime= testDateTime;
	}
	
	@Override
	public LocalDateTime now() {
		// TODO Auto-generated method stub
		return this.testDateTime;
	}



}

package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.time.LocalTime;

public class LocalTimeManager {
	protected LocalTime startHour;
	protected LocalTime endHour;
	
	public LocalTimeManager() {	
		startHour = LocalTime.of(8, 0);
		endHour = LocalTime.of(20, 0);
	}
	
	public LocalTimeManager(LocalTime horaInicio, LocalTime horaFin) {
		this.startHour=horaInicio;
		this.endHour=horaFin;
	}

	private Boolean isAfterTheStartHour(LocalTime time) {
		return !time.isBefore(startHour);
	}
	
	private Boolean isBeforeTheEndHour(LocalTime time) {
		return !time.isAfter(endHour);
	}
 	
	public Boolean isActiveTime() {
		return this.isActiveTime(LocalTime.now());
	}
	public Boolean isActiveTime(LocalTime l) {
		return this.isAfterTheStartHour(l) && this.isBeforeTheEndHour(l) ;
	}
	
	
}

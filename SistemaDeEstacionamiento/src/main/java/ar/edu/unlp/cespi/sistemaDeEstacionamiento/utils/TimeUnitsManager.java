package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeUnitsManager extends LocalTimeManager {
	
	private int timeIntervalInMinutes;
	
	public TimeUnitsManager() {
		this.timeIntervalInMinutes=60;
	}
	public TimeUnitsManager(int timeIntervalInMinutes) {
		if (timeIntervalInMinutes > 0) {
			this.timeIntervalInMinutes=timeIntervalInMinutes;
		} else {
			this.timeIntervalInMinutes=60;
		}
	}
	
	private Boolean hasRemainder(Long minutes) {
		return (minutes % this.timeIntervalInMinutes) != 0;
	}
	
	public int calcularUnidadesDeTiempo(LocalTime startHour, LocalTime endHour) {
        long minutesElapsed = ChronoUnit.MINUTES.between(startHour, endHour);
        if (minutesElapsed == 0) { return 1;}
        int timeUnits = (int) (minutesElapsed / this.timeIntervalInMinutes);
        if (this.hasRemainder(minutesElapsed)){
        	 return timeUnits + 1;
        }
        return timeUnits;
    }
	
}

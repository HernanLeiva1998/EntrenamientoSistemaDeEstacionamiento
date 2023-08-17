package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.time.LocalTime;

public class LocalTimeManager {
	protected LocalTime horaInicio = LocalTime.of(8, 0);
	protected LocalTime horaFin = LocalTime.of(20, 0);
	
	public LocalTimeManager() {	}

	private Boolean esDespuesDelInicio(LocalTime hora) {
		return !hora.isBefore(horaInicio);
	}
	
	private Boolean esAntesDelFin(LocalTime hora) {
		return !hora.isAfter(horaFin);
	}
 	
	public Boolean esHorarioActivo() {
		LocalTime hora = LocalTime.now();
		return this.esDespuesDelInicio(hora) && this.esAntesDelFin(hora) ;
	}
	public Boolean esHorarioActivo(LocalTime l) {
		return this.esDespuesDelInicio(l) && this.esAntesDelFin(l) ;
	}
	
	
}

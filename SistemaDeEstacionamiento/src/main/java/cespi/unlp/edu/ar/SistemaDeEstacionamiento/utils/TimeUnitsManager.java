package cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeUnitsManager extends LocalTimeManager {
	
	private int intervaloEnMinutos;
	
	public TimeUnitsManager() {
		this.intervaloEnMinutos=60;
	}
	public TimeUnitsManager(int intervaloEnMinutos) {
		if (intervaloEnMinutos > 0) {
			this.intervaloEnMinutos=intervaloEnMinutos;
		} else {
			this.intervaloEnMinutos=60;
		}
	}
	
	private Boolean tieneResto(Long minutos) {
		return (minutos % this.intervaloEnMinutos) != 0;
	}
	
	public int calcularUnidadesDeTiempo(LocalTime horaInicio, LocalTime horaFin) {
        long minutosTranscurridos = ChronoUnit.MINUTES.between(horaInicio, horaFin);
        if (minutosTranscurridos == 0) { return 1;}
        int unidadesDeTiempo = (int) (minutosTranscurridos / this.intervaloEnMinutos);
        if (this.tieneResto(minutosTranscurridos)){
        	 return unidadesDeTiempo + 1;
        }
        return unidadesDeTiempo;
    }
	
}

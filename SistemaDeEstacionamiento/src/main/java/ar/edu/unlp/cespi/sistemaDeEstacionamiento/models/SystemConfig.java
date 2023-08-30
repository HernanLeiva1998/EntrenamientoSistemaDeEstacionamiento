package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SystemConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_configuracion_del_sistema")
    private Long id;
	
	@Column(name = "precio_por_hora")
	private Double precioPorHora;
	
	@Column(name = "hora_de_inicio_de_jornada")
	private LocalTime horaDeInicioDeJornada;

	@Column(name = "hora_de_fin_de_jornada")
	private LocalTime horaDeFinDeJornada;
	
	@Column(name = "formato_patente")
	private String formatosPatentes;

	

	public SystemConfig() {
		
	}

	public SystemConfig(Double valor) {
		this.precioPorHora=valor;
		this.horaDeInicioDeJornada= LocalTime.of(8, 0);
		this.horaDeFinDeJornada=LocalTime.of(20, 0);
		this.formatosPatentes="^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$";
	}
	
	public SystemConfig(Double precioPorHora, LocalTime horaDeInicio, LocalTime horaDeFin, String formatosPatentes) {
		this.precioPorHora=precioPorHora;
		this.horaDeInicioDeJornada=horaDeInicio;
		this.horaDeFinDeJornada=horaDeFin;
		this.formatosPatentes=formatosPatentes;
	}
	
	public LocalTime getHoraDeInicioDeJornada() {
		return horaDeInicioDeJornada;
	}

	public void setHoraDeInicioDeJornada(LocalTime horaDeInicioDeJornada) {
		this.horaDeInicioDeJornada = horaDeInicioDeJornada;
	}

	public LocalTime getHoraDeFinDeJornada() {
		return horaDeFinDeJornada;
	}

	public void setHoraDeFinDeJornada(LocalTime horaDeFinDeJornada) {
		this.horaDeFinDeJornada = horaDeFinDeJornada;
	}

	public void cambiarHorario(LocalTime horaInicio, LocalTime horaFin) {
		this.setHoraDeInicioDeJornada(horaInicio);
		this.setHoraDeFinDeJornada(horaFin);
	}
	
	
	public String getFormatosPatentes() {
		return formatosPatentes;
	}

	public void setFormatosPatentes(String formatosPatentes) {
		this.formatosPatentes = formatosPatentes;
	}
	
	
	public void setId(Long id) {
		this.id=id;
	}

	public Double getPrecioPorHora() {
		return precioPorHora;
	}

	public void setPrecioPorHora(Double precioPorHora) {
		this.precioPorHora = precioPorHora;
	}
}

package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {
	
	public Reserva(Automovilista automovilista, Patente patente, LocalDateTime inicioDeReserva) {
		this.inicioDeReserva=inicioDeReserva;
		this.automovilista = automovilista;
		this.patente = patente;
		this.estaActiva = true;
	}

	
	public Reserva(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p) {
		// Solo para test
		this.inicioDeReserva=inicio;
		this.finDeReserva=fin;
		this.automovilista = a;
		this.patente = p;
		this.estaActiva = true;
	}


	@Id
	@Column(name = "id_reserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "inicio_de_reserva", nullable = false)
	private LocalDateTime inicioDeReserva;
	
	@Column(name = "fin_de_reserva", nullable = true)
	private LocalDateTime finDeReserva;
	
	@Column(nullable = true)
	private Double monto;
	
	@Column(nullable = false)
	private Boolean estaActiva;
	
	@ManyToOne
    @JoinColumn(name = "automovilista_id")
    private Automovilista automovilista;

    @ManyToOne
    @JoinColumn(name = "patente_id")
    private Patente patente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInicioDeReserva() {
		return inicioDeReserva;
	}

	public void setInicioDeReserva(LocalDateTime inicioDeReserva) {
		this.inicioDeReserva = inicioDeReserva;
	}

	public LocalDateTime getFinDeReserva() {
		return finDeReserva;
	}

	public void setFinDeReserva(LocalDateTime finDeReserva) {
		this.finDeReserva = finDeReserva;
	}
	
	public void finalizarReserva(double monto) {
		this.setMonto(monto);
		this.setEstaActiva(false);
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Automovilista getAutomovilista() {
		return automovilista;
	}

	public void setAutomovilista(Automovilista automovilista) {
		this.automovilista = automovilista;
	}

	public Patente getPatente() {
		return patente;
	}

	public void setPatente(Patente patente) {
		this.patente = patente;
	}

	public Boolean getEstaActiva() {
		return estaActiva;
	}

	public void setEstaActiva(Boolean estaActiva) {
		this.estaActiva = estaActiva;
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Reserva otraReserva = (Reserva) obj;
        
        return Objects.equals(id, otraReserva.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

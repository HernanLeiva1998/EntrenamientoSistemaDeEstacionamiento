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
public class Estacionamiento {
	
	public Estacionamiento() {}
	
	public Estacionamiento(Automovilista automovilista, Patente patente, LocalDateTime inicioDeEstacionamiento) {
		this.inicioDeEstacionamiento=inicioDeEstacionamiento;
		this.automovilista = automovilista;
		this.patente = patente;
		this.estaActivo = true;
	}

	
	public Estacionamiento(LocalDateTime inicio, LocalDateTime fin, Automovilista a, Patente p) {
		// Solo para test
		this.inicioDeEstacionamiento=inicio;
		this.finDeEstacionamiento=fin;
		this.automovilista = a;
		this.patente = p;
		this.estaActivo = true;
	}


	@Id
	@Column(name = "id_estacionamiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "inicio_de_estacionamiento", nullable = false)
	private LocalDateTime inicioDeEstacionamiento;
	
	@Column(name = "fin_de_estacionamiento", nullable = true)
	private LocalDateTime finDeEstacionamiento;
	
	@Column(nullable = true)
	private Double monto;
	
	@Column(nullable = false)
	private Boolean estaActivo;
	
	@ManyToOne
    @JoinColumn(name = "id_automovilista")
    private Automovilista automovilista;

    @ManyToOne
    @JoinColumn(name = "id_patente")
    private Patente patente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInicioDeEstacionamiento() {
		return inicioDeEstacionamiento;
	}

	public void setInicioDeEstacionamiento(LocalDateTime inicioDeEstacionamiento) {
		this.inicioDeEstacionamiento = inicioDeEstacionamiento;
	}

	public LocalDateTime getFinDeEstacionamiento() {
		return finDeEstacionamiento;
	}

	public void setFinDeEstacionamiento(LocalDateTime finDeEstacionamiento) {
		this.finDeEstacionamiento = finDeEstacionamiento;
	}
	
	public void finalizarEstacionamiento(double monto) {
		this.setMonto(monto);
		this.setEstaActivo(false);
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

	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Estacionamiento otraEstacionamiento = (Estacionamiento) obj;
        
        return Objects.equals(id, otraEstacionamiento.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

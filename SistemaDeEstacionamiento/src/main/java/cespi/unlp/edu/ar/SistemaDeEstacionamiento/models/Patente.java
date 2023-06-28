package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;



import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Patente {
	@Id
	@Column(name = "id_patente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable= false, unique = true)
    private String patente;
	
	@Column(name = "tiene_estacionamiento_iniciado")
	private Boolean tieneEstacionamientoIniciado;

	@ManyToMany(mappedBy = "patentes")
    private List<Automovilista> automovilistas = new ArrayList<>();
	
	

	@OneToMany(mappedBy = "patente")
    private List<Reserva> reservas = new ArrayList<>();

    // Constructor, getters y setters
    public Patente() {
    	
    }
    
    public Patente(String patente) {
    	this.patente=patente;
    	this.tieneEstacionamientoIniciado = false;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public List<Automovilista> getAutomovilistas() {
		return automovilistas;
	}

	public Boolean getTieneEstacionamientoIniciado() {
		return tieneEstacionamientoIniciado;
	}

	public void setTieneEstacionamientoIniciado(Boolean tieneEstacionamientoIniciado) {
		this.tieneEstacionamientoIniciado = tieneEstacionamientoIniciado;
	}
	
	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}

	
    
    
}
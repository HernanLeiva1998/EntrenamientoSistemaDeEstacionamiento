package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name = "id_automovilista")
    private Automovilista automovilista;

    // Constructor, getters y setters
    public Patente() {
    	
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

	public Boolean getTieneEstacionamientoIniciado() {
		return tieneEstacionamientoIniciado;
	}

	public void setTieneEstacionamientoIniciado(Boolean tieneEstacionamientoIniciado) {
		this.tieneEstacionamientoIniciado = tieneEstacionamientoIniciado;
	}

	public Automovilista getAutomovilista() {
		return automovilista;
	}

	public void setAutomovilista(Automovilista automovilista) {
		this.automovilista = automovilista;
	}
    
    public 
    
    
}
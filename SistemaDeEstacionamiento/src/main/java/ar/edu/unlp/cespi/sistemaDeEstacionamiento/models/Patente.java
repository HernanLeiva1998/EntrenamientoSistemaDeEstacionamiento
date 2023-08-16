package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
@Entity
public class Patente {
	@Id
	@Column(name = "id_patente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable= false, unique = true)
    private String patente;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "patentes")
    private List<Automovilista> automovilistas = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "patente")
    private List<Estacionamiento> estacionamientos = new ArrayList<>();

    // Constructor, getters y setters
	
	public Patente() {
    	
    }
	
    public Patente(String patenteString) {
    	this.patente = patenteString;
    	
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
	
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamientos.add(estacionamiento);
	}

	
    
    
}
package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Automovilista {
	
	@Id
	@Column(name = "id_automovilista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String telefono;

    @Column(nullable = false)
    private String contraseña;
    
    @Column(name = "tiene_estacionamiento_iniciado")
	private Boolean tieneEstacionamientoIniciado;

    @OneToMany(mappedBy = "automovilista")
    private List<Patente> patentes;

    @OneToMany(mappedBy = "automovilista")
    private List<CuentaCorriente> cuentasCorrientes;

    // Constructor, getters y setters
    public Automovilista() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Boolean getTieneEstacionamientoIniciado() {
		return tieneEstacionamientoIniciado;
	}

	public void setTieneEstacionamientoIniciado(Boolean tieneEstacionamientoIniciado) {
		this.tieneEstacionamientoIniciado = tieneEstacionamientoIniciado;
	}

	public List<Patente> getPatentes() {
		return patentes;
	}

	public void setPatentes(List<Patente> patentes) {
		this.patentes = patentes;
	}

	public List<CuentaCorriente> getCuentasCorrientes() {
		return cuentasCorrientes;
	}

	public void setCuentasCorrientes(List<CuentaCorriente> cuentasCorrientes) {
		this.cuentasCorrientes = cuentasCorrientes;
	}
}

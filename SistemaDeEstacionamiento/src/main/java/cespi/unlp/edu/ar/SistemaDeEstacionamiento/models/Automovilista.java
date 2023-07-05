package cespi.unlp.edu.ar.SistemaDeEstacionamiento.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Automovilista {
	
	

	@Id
	@Column(name = "id_automovilista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String telefono;
	
	@JsonIgnore
	@Column(nullable = false)
	private String contraseña;


	@Column(name = "tiene_estacionamiento_iniciado")
	private Boolean tieneEstacionamientoIniciado;

    @ManyToMany
    @JoinTable(
            name = "automovilista_patente",
            joinColumns = @JoinColumn(name = "id_automovilista"),
            inverseJoinColumns = @JoinColumn(name = "id_patente")
    )
    private List<Patente> patentes = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_cuenta_corriente")
    private CuentaCorriente cuentaCorriente;
    
    @OneToMany(mappedBy = "automovilista")
    private List<Reserva> reservas = new ArrayList<>();
    
    public Boolean puedeIniciarReserva(Double monto) {
    	return !this.getCuentaCorriente().saldoEsInferiorA(monto);
    }
    
    public void restarSaldo(Double monto) {
		this.cuentaCorriente.restarSaldo(monto);
		
	}
    
    // Constructor, getters y setters
    public Automovilista() {
    	
    }
    
    public Automovilista(String telefono, String contraseña) {
		this.telefono = telefono;
		this.contraseña = contraseña;
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
		return this.contraseña;
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

	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public void iniciarEstacionamiento(Patente patente) {
		
	}
	public void finalizarEstacionamiento() {
		
	}
	
	public void addPatente(Patente patente) {
		this.patentes.add(patente);
	}
	
	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automovilista that = (Automovilista) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telefono);
    }

	
}

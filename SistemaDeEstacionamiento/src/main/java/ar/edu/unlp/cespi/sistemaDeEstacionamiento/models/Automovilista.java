package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@AllArgsConstructor
public class Automovilista implements UserDetails{
	
	

	@Id
	@Column(name = "id_automovilista")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String telefono;
	
	@Column(unique = true)
    private String email;
	
	@JsonIgnore
	@Column(nullable = false)
	private String contrasena;

    @ManyToMany
    @JoinTable(
            name = "automovilista_patente",
            joinColumns = @JoinColumn(name = "id_automovilista"),
            inverseJoinColumns = @JoinColumn(name = "id_patente")
    )
    private List<Patente> patentes;

    @OneToOne
    @JoinColumn(name = "id_cuenta_corriente")
    private CuentaCorriente cuentaCorriente;
    
    @OneToMany(mappedBy = "automovilista")
    private List<Estacionamiento> estacionamientos;
    
    @Enumerated(EnumType.STRING) 
    Role role;
    
    // Constructor, getters y setters
    public Automovilista() {
    	
    }
    
    public Automovilista(String telefono, String contraseña) {
		this.telefono = telefono;
		this.contrasena = contraseña;
	}
    
    public Automovilista(String telefono, String contraseña, String email) {
		this.telefono = telefono;
		this.contrasena = contraseña;
		this.email = email;
	}
    
    public Automovilista(String telefono, String contraseña, String email, Role role) {
		this.telefono = telefono;
		this.contrasena = contraseña;
		this.email = email;
		this.role=role;
	}
    
    public Boolean puedeIniciarEstacionamiento(Double monto) {
    	return !this.getCuentaCorriente().saldoEsInferiorA(monto);
    }
    
    public void restarSaldo(Double monto) {
		this.cuentaCorriente.restarSaldo(monto);
		
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

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
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
	
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamientos.add(estacionamiento);
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

	public boolean tienePatenteAsignada(String patenteString) {
		for (Patente patente : patentes) {
	        if (patente.getPatente().toLowerCase().equals(patenteString.toLowerCase())) {
	            return true; // La patente existe en la lista
	        }
	    }
		return false;
	}

	public String getEmail() {
		return this.email;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getContrasena();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return  this.getTelefono();
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

	
}

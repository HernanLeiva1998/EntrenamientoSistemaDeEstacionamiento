package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;

import java.util.Collection;
import java.util.List;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Driver implements UserDetails{
	
	

	@Id
	@Column(name = "id_driver")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String phone;
	
	@Column(unique = true)
    private String email;
	
	@JsonIgnore
	@Column(nullable = false)
	private String password;

    @ManyToMany
    @JoinTable(
            name = "driver_licensePlate",
            joinColumns = @JoinColumn(name = "id_driver"),
            inverseJoinColumns = @JoinColumn(name = "id_license_plate")
    )
    private List<LicensePlate> licensePlates;

    @OneToOne
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;
    
    @OneToMany(mappedBy = "driver")
    private List<Parking> parkings;
    
    @Enumerated(EnumType.STRING) 
    Role role;
    
    // Constructor, getters y setters
    public Driver() {
    	
    }
    
    public Driver(String phone, String password) {
		this.phone = phone;
		this.password = password;
		this.role=Role.USER;
	}
    
    public Driver(String phone, String password, String email) {
		this.phone = phone;
		this.password = password;
		this.email = email;
	}
    
    public Driver(String phone, String password, String email, Role role) {
		this.phone = phone;
		this.password = password;
		this.email = email;
		this.role=role;
	}
    
    public Boolean canStartParking(Double monto) {
    	return !this.getWallet().balanceIsLesserThan(monto);
    }
    
    public void subtractBalance(Double amount) {
		this.wallet.subtractBalance(amount);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContrasena() {
		return this.password;
	}

	public void setContrasena(String password) {
		this.password = password;
	}

	public List<LicensePlate> getPatentes() {
		return licensePlates;
	}

	public void setPatentes(List<LicensePlate> licensePlates) {
		this.licensePlates = licensePlates;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public void addLicensePlate(LicensePlate licensePlate) {
		this.licensePlates.add(licensePlate);
	}
	
	public void addParking(Parking estacionamiento) {
		this.parkings.add(estacionamiento);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver that = (Driver) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone);
    }

	public boolean hasLicensePlateAssigned(String licensePlateString) {
		for (LicensePlate licensePlate : licensePlates) {
	        if (licensePlate.getLicensePlate().toLowerCase().equals(licensePlateString.toLowerCase())) {
	            return true; // La licensePlate existe en la lista
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
		return  this.getPhone();
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

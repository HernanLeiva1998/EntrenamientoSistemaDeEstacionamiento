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
public class LicensePlate {
	@Id
	@Column(name = "id_license_plate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable= false, unique = true)
    private String licensePlate;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "licensePlates")
    private List<Driver> drivers = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "licensePlate")
    private List<Parking> parkings = new ArrayList<>();

    // Constructor, getters y setters
	
	public LicensePlate() {
    	
    }
	
    public LicensePlate(String licensePlateString) {
    	this.licensePlate = licensePlateString;
    	
    }
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public List<Driver> getDrivers() {
		return drivers;
	}
	
	public void addParking(Parking parking) {
		this.parkings.add(parking);
	}

	
    
    
}
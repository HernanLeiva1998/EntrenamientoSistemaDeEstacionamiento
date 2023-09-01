package ar.edu.unlp.cespi.sistemaDeEstacionamiento.models;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Parking {
	
	public Parking() {}
	
	public Parking(Driver driver, LicensePlate patente, LocalDateTime inicioDeEstacionamiento) {
		this.parkingStart=inicioDeEstacionamiento;
		this.driver = driver;
		this.licensePlate = patente;
		this.isActive = true;
	}

	
	public Parking(LocalDateTime inicio, LocalDateTime fin, Driver a, LicensePlate p) {
		// Solo para test
		this.parkingStart=inicio;
		this.parkingEnd=fin;
		this.driver = a;
		this.licensePlate = p;
		this.isActive = true;
	}


	@Id
	@Column(name = "id_parking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "parking_start", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime parkingStart;
	
	@Column(name = "parking_end", nullable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime parkingEnd;
	
	@Column(nullable = true)
	private Double totalCost;
	
	@Column(nullable = false)
	private Boolean isActive;
	
	@ManyToOne
    @JoinColumn(name = "id_driver")
	@JsonIgnore
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "id_license_plate")
    @JsonIgnore
    private LicensePlate licensePlate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getParkingStart() {
		return parkingStart;
	}

	public void setParkingStart(LocalDateTime inicioDeEstacionamiento) {
		this.parkingStart = inicioDeEstacionamiento;
	}

	public LocalDateTime geParkingEnd() {
		return parkingEnd;
	}

	public void setParkingEnd(LocalDateTime finDeEstacionamiento) {
		this.parkingEnd = finDeEstacionamiento;
	}
	
	public void setTotalCostAndChangeStateToInactive(double monto) {
		this.setTotalCost(monto);
		this.setIsActive(false);
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double monto) {
		this.totalCost = monto;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public LicensePlate getLicensePlate() {
		return licensePlate;
	}

	public void setlicensePlate(LicensePlate patente) {
		this.licensePlate = patente;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setIsActive(Boolean estaActivo) {
		this.isActive = estaActivo;
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Parking anotherParking = (Parking) obj;
        
        return Objects.equals(id, anotherParking.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

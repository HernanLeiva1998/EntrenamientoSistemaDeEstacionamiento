package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
	@Query("SELECT d "
			+ "FROM Driver d JOIN d.parkings p "
			+ "where d.id = :driverId and p.isActive = true")
	Optional<Driver> findDriverByIdAndExistsActiveParking(@Param("driverId") Long driverId);

	 Optional<Driver> findByPhone(String phone);

	 Optional<Driver> findByEmail(String email);
}

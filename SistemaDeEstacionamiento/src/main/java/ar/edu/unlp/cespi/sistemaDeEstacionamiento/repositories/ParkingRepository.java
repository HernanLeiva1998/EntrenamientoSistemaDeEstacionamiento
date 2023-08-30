package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Parking;

public interface ParkingRepository extends CrudRepository<Parking, Long> {

	@Query(value="Select p from Parking p where p.isActive = true and p.driver.id = :id ")
	Optional<Parking> findByActive(@Param("id")Long id);

}

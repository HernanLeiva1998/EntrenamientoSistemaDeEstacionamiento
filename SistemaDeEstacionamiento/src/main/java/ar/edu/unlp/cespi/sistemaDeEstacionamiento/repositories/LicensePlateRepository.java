package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.LicensePlate;

public interface LicensePlateRepository extends CrudRepository<LicensePlate, Long> {

	Optional<LicensePlate> findByLicensePlate(String licensePlate);

	@Query("SELECT p "
			+ "FROM LicensePlate lp JOIN lp.parkings p "
			+ "where lp.id = :licensePlateId and p.isActive = true")
	Optional<LicensePlate> findByIdAndExistsActiveParking(@Param("licensePlateId") Long licensePlateId);

	List<LicensePlate> findByDriversPhone(String phone);

}

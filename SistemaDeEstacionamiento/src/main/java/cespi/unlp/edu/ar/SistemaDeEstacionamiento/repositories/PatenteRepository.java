package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;

public interface PatenteRepository extends CrudRepository<Patente, Long> {

	Optional<Patente> findByPatente(String patente);

	@Query("SELECT p "
			+ "FROM Patente p JOIN p.reservas r "
			+ "where p.id = :patenteId and r.estaActiva = true")
	Optional<Patente> findByIdAndExistingReservaActiva(@Param("patenteId") Long patenteId);

	List<Patente> findByAutomovilistasTelefono(String telefono);

}

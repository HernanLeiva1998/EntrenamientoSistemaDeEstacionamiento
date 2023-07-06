package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;

public interface AutomovilistaRepository extends CrudRepository<Automovilista, Long> {
	@Query("SELECT a "
			+ "FROM Automovilista a JOIN a.reservas r "
			+ "where a.id = :automovilistaId and r.estaActiva = true")
	Optional<Automovilista> findByIdAndExistingReservaActiva(@Param("automovilistaId") Long automovilistaId);

	 Optional<Automovilista> findByTelefono(String telefono);
}

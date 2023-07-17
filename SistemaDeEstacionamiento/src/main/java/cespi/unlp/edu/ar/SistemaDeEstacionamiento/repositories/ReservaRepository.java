package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

	@Query(value="Select r from Reserva r where r.estaActiva = true and r.automovilista.id = :id ")
	Optional<Reserva> findByActiva(@Param("id")Long id);

}

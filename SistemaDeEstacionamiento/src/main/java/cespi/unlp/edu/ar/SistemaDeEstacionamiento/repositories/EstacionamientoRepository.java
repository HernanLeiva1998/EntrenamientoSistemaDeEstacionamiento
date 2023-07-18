package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Estacionamiento;

public interface EstacionamientoRepository extends CrudRepository<Estacionamiento, Long> {

	@Query(value="Select e from Estacionamiento e where e.estaActivo = true and e.automovilista.id = :id ")
	Optional<Estacionamiento> findByActivo(@Param("id")Long id);

}

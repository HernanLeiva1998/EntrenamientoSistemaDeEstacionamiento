package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;

public interface PatenteRepository extends CrudRepository<Patente, Long> {

	Optional<Patente> findByPatente(String patente);

}

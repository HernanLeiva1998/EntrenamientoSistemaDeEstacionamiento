package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Patente;

public interface PatenteRepository extends CrudRepository<Patente, Long> {

	Optional<Patente> findByPatente(String patente);

	@Query("SELECT p "
			+ "FROM Patente p JOIN p.estacionamientos e "
			+ "where p.id = :patenteId and e.estaActivo = true")
	Optional<Patente> findByIdAndExistingEstacionamientoActivo(@Param("patenteId") Long patenteId);

	List<Patente> findByAutomovilistasTelefono(String telefono);

}

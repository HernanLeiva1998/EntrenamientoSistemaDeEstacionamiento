package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Automovilista;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.CuentaCorriente;

public interface CuentaCorrienteRepository extends CrudRepository<CuentaCorriente, Long> {

	Optional<CuentaCorriente> findByCbu(String cbu);

}

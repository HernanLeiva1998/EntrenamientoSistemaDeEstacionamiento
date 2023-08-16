package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.CuentaCorriente;

public interface CuentaCorrienteRepository extends CrudRepository<CuentaCorriente, Long> {

	Optional<CuentaCorriente> findByCbu(String cbu);

}

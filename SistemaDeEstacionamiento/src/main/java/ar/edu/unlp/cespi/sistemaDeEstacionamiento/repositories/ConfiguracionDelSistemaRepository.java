package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.SystemConfig;

public interface ConfiguracionDelSistemaRepository extends CrudRepository<SystemConfig, Long> {
}

package ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.ConfiguracionDelSistema;

public interface ConfiguracionDelSistemaRepository extends CrudRepository<ConfiguracionDelSistema, Long> {
}

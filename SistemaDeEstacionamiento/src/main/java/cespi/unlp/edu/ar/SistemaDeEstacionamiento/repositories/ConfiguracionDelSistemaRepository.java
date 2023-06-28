package cespi.unlp.edu.ar.SistemaDeEstacionamiento.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.ConfiguracionDelSistema;

public interface ConfiguracionDelSistemaRepository extends CrudRepository<ConfiguracionDelSistema, Long> {

}

package quileia.com.repository;

import quileia.com.domain.Especialidad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import quileia.com.domain.enumeration.Estado;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Especialidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    Optional<Especialidad> findByNombreEspecialidad(String nombreEspecialidad);

    List<Especialidad> findByEstadoEspecialidad(Estado estado);
}

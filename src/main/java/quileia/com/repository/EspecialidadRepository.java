package quileia.com.repository;

import quileia.com.domain.Especialidad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Especialidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}

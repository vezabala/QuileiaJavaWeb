package quileia.com.repository;

import quileia.com.domain.Horario;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Horario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long>,JpaSpecificationExecutor<Horario> {
    Optional<Horario> findByHora(String hora);
}

package quileia.com.repository;

import quileia.com.domain.FranjaHoraria;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import quileia.com.domain.enumeration.Estado;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FranjaHoraria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Long> {
    Optional<FranjaHoraria> findByFranja(String franja);

    List<FranjaHoraria> findByEstadoFranjaHoraria(Estado estado);
}

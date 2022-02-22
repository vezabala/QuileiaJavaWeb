package quileia.com.repository;

import quileia.com.domain.FranjaHoraria;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FranjaHoraria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Long> {
}

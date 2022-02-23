package quileia.com.repository;

import quileia.com.domain.Medico;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import quileia.com.domain.TipoDocumento;

import java.util.Optional;

/**
 * Spring Data  repository for the Medico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByIdentificacionAndTipoDocumento(String identificacion, TipoDocumento tipoDocumento);
}

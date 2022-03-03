package quileia.com.repository;

import quileia.com.domain.TipoDocumento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import quileia.com.domain.enumeration.Estado;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TipoDocumento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    Optional<TipoDocumento> findByIniciales(String iniciales);

    Optional<TipoDocumento> findByNombreDocumento(String nombreDocumento);

    List<TipoDocumento> findByEstadoTipoDocumento(Estado estado);
}

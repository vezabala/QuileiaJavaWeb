package quileia.com.repository;

import quileia.com.domain.Paciente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import quileia.com.domain.TipoDocumento;

import java.util.Optional;

/**
 * Spring Data  repository for the Paciente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByIdentificacionAndTipoDocumento(String identificacion, TipoDocumento tipoDocumento);
}

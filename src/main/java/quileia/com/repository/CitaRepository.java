package quileia.com.repository;

import quileia.com.domain.Cita;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import quileia.com.domain.Horario;
import quileia.com.domain.Medico;
import quileia.com.domain.Paciente;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Spring Data  repository for the Cita entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    Optional<Cita> findByMedicosAndHorarioAndFecha(Medico medico, Horario horario, LocalDate fecha);
    Optional<Cita> findByPacientesAndHorarioAndFecha(Paciente paciente, Horario horario, LocalDate fecha);
    Optional<Cita> findByPacientesAndMedicos(Paciente paciente, Medico medico);
}

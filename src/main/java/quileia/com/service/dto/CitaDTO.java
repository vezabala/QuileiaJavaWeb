package quileia.com.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link quileia.com.domain.Cita} entity.
 */
public class CitaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate fecha;


    private Long especialidadId;

    private String especialidadNombreEspecialidad;

    private Long franjaHorariaId;

    private String franjaHorariaFranja;

    private Long horarioId;

    private String horarioHora;

    private Long medicosId;

    private String medicosNombreCompleto;

    private Long pacientesId;

    private String pacientesNombreCompleto;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getEspecialidadNombreEspecialidad() {
        return especialidadNombreEspecialidad;
    }

    public void setEspecialidadNombreEspecialidad(String especialidadNombreEspecialidad) {
        this.especialidadNombreEspecialidad = especialidadNombreEspecialidad;
    }

    public Long getFranjaHorariaId() {
        return franjaHorariaId;
    }

    public void setFranjaHorariaId(Long franjaHorariaId) {
        this.franjaHorariaId = franjaHorariaId;
    }

    public String getFranjaHorariaFranja() {
        return franjaHorariaFranja;
    }

    public void setFranjaHorariaFranja(String franjaHorariaFranja) {
        this.franjaHorariaFranja = franjaHorariaFranja;
    }

    public Long getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Long horarioId) {
        this.horarioId = horarioId;
    }

    public String getHorarioHora() {
        return horarioHora;
    }

    public void setHorarioHora(String horarioHora) {
        this.horarioHora = horarioHora;
    }

    public Long getMedicosId() {
        return medicosId;
    }

    public void setMedicosId(Long medicoId) {
        this.medicosId = medicoId;
    }

    public String getMedicosNombreCompleto() {
        return medicosNombreCompleto;
    }

    public void setMedicosNombreCompleto(String medicoNombreCompleto) {
        this.medicosNombreCompleto = medicoNombreCompleto;
    }

    public Long getPacientesId() {
        return pacientesId;
    }

    public void setPacientesId(Long pacienteId) {
        this.pacientesId = pacienteId;
    }

    public String getPacientesNombreCompleto() {
        return pacientesNombreCompleto;
    }

    public void setPacientesNombreCompleto(String pacienteNombreCompleto) {
        this.pacientesNombreCompleto = pacienteNombreCompleto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CitaDTO citaDTO = (CitaDTO) o;
        if (citaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), citaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CitaDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", especialidadId=" + getEspecialidadId() +
            ", especialidadNombreEspecialidad='" + getEspecialidadNombreEspecialidad() + "'" +
            ", franjaHorariaId=" + getFranjaHorariaId() +
            ", franjaHorariaFranja='" + getFranjaHorariaFranja() + "'" +
            ", horarioId=" + getHorarioId() +
            ", horarioHora='" + getHorarioHora() + "'" +
            ", medicosId=" + getMedicosId() +
            ", medicosNombreCompleto='" + getMedicosNombreCompleto() + "'" +
            ", pacientesId=" + getPacientesId() +
            ", pacientesNombreCompleto='" + getPacientesNombreCompleto() + "'" +
            "}";
    }
}

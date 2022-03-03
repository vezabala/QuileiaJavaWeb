package quileia.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import quileia.com.domain.enumeration.Estado;

/**
 * A DTO for the {@link quileia.com.domain.Especialidad} entity.
 */
public class EspecialidadDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombreEspecialidad;

    @NotNull
    private Estado estadoEspecialidad;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Estado getEstadoEspecialidad() {
        return estadoEspecialidad;
    }

    public void setEstadoEspecialidad(Estado estadoEspecialidad) {
        this.estadoEspecialidad = estadoEspecialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EspecialidadDTO especialidadDTO = (EspecialidadDTO) o;
        if (especialidadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspecialidadDTO{" +
            "id=" + getId() +
            ", nombreEspecialidad='" + getNombreEspecialidad() + "'" +
            ", estadoEspecialidad='" + getEstadoEspecialidad() + "'" +
            "}";
    }
}

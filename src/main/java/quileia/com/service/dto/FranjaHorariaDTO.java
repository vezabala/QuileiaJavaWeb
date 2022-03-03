package quileia.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import quileia.com.domain.enumeration.Estado;

/**
 * A DTO for the {@link quileia.com.domain.FranjaHoraria} entity.
 */
public class FranjaHorariaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 30)
    private String franja;

    @NotNull
    private Estado estadoFranjaHoraria;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFranja() {
        return franja;
    }

    public void setFranja(String franja) {
        this.franja = franja;
    }

    public Estado getEstadoFranjaHoraria() {
        return estadoFranjaHoraria;
    }

    public void setEstadoFranjaHoraria(Estado estadoFranjaHoraria) {
        this.estadoFranjaHoraria = estadoFranjaHoraria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FranjaHorariaDTO franjaHorariaDTO = (FranjaHorariaDTO) o;
        if (franjaHorariaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), franjaHorariaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FranjaHorariaDTO{" +
            "id=" + getId() +
            ", franja='" + getFranja() + "'" +
            ", estadoFranjaHoraria='" + getEstadoFranjaHoraria() + "'" +
            "}";
    }
}

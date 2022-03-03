package quileia.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link quileia.com.domain.Horario} entity.
 */
public class HorarioDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String hora;


    private Long franjaHorariaId;

    private String franjaHorariaFranja;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HorarioDTO horarioDTO = (HorarioDTO) o;
        if (horarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), horarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HorarioDTO{" +
            "id=" + getId() +
            ", hora='" + getHora() + "'" +
            ", franjaHorariaId=" + getFranjaHorariaId() +
            ", franjaHorariaFranja='" + getFranjaHorariaFranja() + "'" +
            "}";
    }
}

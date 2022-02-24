package quileia.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link quileia.com.domain.Medico} entity.
 */
public class MedicoDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombreCompleto;

    @NotNull
    @Size(max = 100)
    private String identificacion;

    @NotNull
    @Size(max = 100)
    private String tarjetaProfesional;

    private Double anosDeExperiencia;


    private Long tipoDocumentoId;

    private String tipoDocumentoNombreDocumento;

    private Long especialidadId;

    private String especialidadNombreEspecialidad;

    private Long franjaHorariaId;

    private String franjaHorariaFranja;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public Double getAnosDeExperiencia() {
        return anosDeExperiencia;
    }

    public void setAnosDeExperiencia(Double anosDeExperiencia) {
        this.anosDeExperiencia = anosDeExperiencia;
    }

    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getTipoDocumentoNombreDocumento() {
        return tipoDocumentoNombreDocumento;
    }

    public void setTipoDocumentoNombreDocumento(String tipoDocumentoNombreDocumento) {
        this.tipoDocumentoNombreDocumento = tipoDocumentoNombreDocumento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicoDTO medicoDTO = (MedicoDTO) o;
        if (medicoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicoDTO{" +
            "id=" + getId() +
            ", nombreCompleto='" + getNombreCompleto() + "'" +
            ", identificacion='" + getIdentificacion() + "'" +
            ", tarjetaProfesional='" + getTarjetaProfesional() + "'" +
            ", anosDeExperiencia=" + getAnosDeExperiencia() +
            ", tipoDocumentoId=" + getTipoDocumentoId() +
            ", tipoDocumentoNombreDocumento='" + getTipoDocumentoNombreDocumento() + "'" +
            ", especialidadId=" + getEspecialidadId() +
            ", especialidadNombreEspecialidad='" + getEspecialidadNombreEspecialidad() + "'" +
            ", franjaHorariaId=" + getFranjaHorariaId() +
            ", franjaHorariaFranja='" + getFranjaHorariaFranja() + "'" +
            "}";
    }
}

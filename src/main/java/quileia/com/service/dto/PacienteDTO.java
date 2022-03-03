package quileia.com.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link quileia.com.domain.Paciente} entity.
 */
public class PacienteDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 250)
    private String nombreCompleto;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    @Size(max = 100)
    private String identificacion;

    @NotNull
    @Size(max = 250)
    private String eps;

    @Lob
    private String historiaClinica;


    private Long tipoDocumentoId;

    private String tipoDocumentoNombreDocumento;
    
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(String historiaClinica) {
        this.historiaClinica = historiaClinica;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PacienteDTO pacienteDTO = (PacienteDTO) o;
        if (pacienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
            "id=" + getId() +
            ", nombreCompleto='" + getNombreCompleto() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", identificacion='" + getIdentificacion() + "'" +
            ", eps='" + getEps() + "'" +
            ", historiaClinica='" + getHistoriaClinica() + "'" +
            ", tipoDocumentoId=" + getTipoDocumentoId() +
            ", tipoDocumentoNombreDocumento='" + getTipoDocumentoNombreDocumento() + "'" +
            "}";
    }
}

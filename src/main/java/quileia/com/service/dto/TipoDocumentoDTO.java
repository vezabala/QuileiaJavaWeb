package quileia.com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import quileia.com.domain.enumeration.Estado;

/**
 * A DTO for the {@link quileia.com.domain.TipoDocumento} entity.
 */
public class TipoDocumentoDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String iniciales;

    @NotNull
    @Size(max = 100)
    private String nombreDocumento;

    @NotNull
    private Estado estadoTipoDocumento;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Estado getEstadoTipoDocumento() {
        return estadoTipoDocumento;
    }

    public void setEstadoTipoDocumento(Estado estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipoDocumentoDTO tipoDocumentoDTO = (TipoDocumentoDTO) o;
        if (tipoDocumentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoDocumentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoDocumentoDTO{" +
            "id=" + getId() +
            ", iniciales='" + getIniciales() + "'" +
            ", nombreDocumento='" + getNombreDocumento() + "'" +
            ", estadoTipoDocumento='" + getEstadoTipoDocumento() + "'" +
            "}";
    }
}

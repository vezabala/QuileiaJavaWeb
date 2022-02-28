package quileia.com.Criteria;

import io.github.jhipster.service.filter.StringFilter;

public class PacienteCriteria {
    private StringFilter identificacion;
    private StringFilter nombreCompleto;

    public StringFilter getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(StringFilter identificacion) {
        this.identificacion = identificacion;
    }

    public StringFilter getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(StringFilter nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}

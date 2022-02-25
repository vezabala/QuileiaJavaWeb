package quileia.com.Criteria;

import io.github.jhipster.service.filter.StringFilter;

public class MedicoCriteria {
    private StringFilter identificacion;
    private StringFilter nombreCompleto;
    private StringFilter franjaHoraria;

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

    public StringFilter getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(StringFilter franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
}

package quileia.com.Criteria;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

public class CitaCriteria {
    private StringFilter medico;
    private LongFilter id;

    public StringFilter getMedico() {
        return medico;
    }

    public void setMedico(StringFilter medico) {
        this.medico = medico;
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }
}

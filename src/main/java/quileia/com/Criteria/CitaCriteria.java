package quileia.com.Criteria;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

public class CitaCriteria {
    private StringFilter medico;
    private StringFilter paciente;

    public StringFilter getMedico() {
        return medico;
    }

    public void setMedico(StringFilter medico) {
        this.medico = medico;
    }

    public StringFilter getPaciente() {
        return paciente;
    }

    public void setPaciente(StringFilter paciente) {
        this.paciente = paciente;
    }
}

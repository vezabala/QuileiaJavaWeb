package quileia.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class PacienteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paciente.class);
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);
        Paciente paciente2 = new Paciente();
        paciente2.setId(paciente1.getId());
        assertThat(paciente1).isEqualTo(paciente2);
        paciente2.setId(2L);
        assertThat(paciente1).isNotEqualTo(paciente2);
        paciente1.setId(null);
        assertThat(paciente1).isNotEqualTo(paciente2);
    }
}

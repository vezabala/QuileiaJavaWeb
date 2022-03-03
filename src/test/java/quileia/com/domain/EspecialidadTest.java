package quileia.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class EspecialidadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Especialidad.class);
        Especialidad especialidad1 = new Especialidad();
        especialidad1.setId(1L);
        Especialidad especialidad2 = new Especialidad();
        especialidad2.setId(especialidad1.getId());
        assertThat(especialidad1).isEqualTo(especialidad2);
        especialidad2.setId(2L);
        assertThat(especialidad1).isNotEqualTo(especialidad2);
        especialidad1.setId(null);
        assertThat(especialidad1).isNotEqualTo(especialidad2);
    }
}

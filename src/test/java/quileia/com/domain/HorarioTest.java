package quileia.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class HorarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Horario.class);
        Horario horario1 = new Horario();
        horario1.setId(1L);
        Horario horario2 = new Horario();
        horario2.setId(horario1.getId());
        assertThat(horario1).isEqualTo(horario2);
        horario2.setId(2L);
        assertThat(horario1).isNotEqualTo(horario2);
        horario1.setId(null);
        assertThat(horario1).isNotEqualTo(horario2);
    }
}

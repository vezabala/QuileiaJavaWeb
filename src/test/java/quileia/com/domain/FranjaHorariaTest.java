package quileia.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class FranjaHorariaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FranjaHoraria.class);
        FranjaHoraria franjaHoraria1 = new FranjaHoraria();
        franjaHoraria1.setId(1L);
        FranjaHoraria franjaHoraria2 = new FranjaHoraria();
        franjaHoraria2.setId(franjaHoraria1.getId());
        assertThat(franjaHoraria1).isEqualTo(franjaHoraria2);
        franjaHoraria2.setId(2L);
        assertThat(franjaHoraria1).isNotEqualTo(franjaHoraria2);
        franjaHoraria1.setId(null);
        assertThat(franjaHoraria1).isNotEqualTo(franjaHoraria2);
    }
}

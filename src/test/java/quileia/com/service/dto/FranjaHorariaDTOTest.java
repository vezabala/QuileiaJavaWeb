package quileia.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class FranjaHorariaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FranjaHorariaDTO.class);
        FranjaHorariaDTO franjaHorariaDTO1 = new FranjaHorariaDTO();
        franjaHorariaDTO1.setId(1L);
        FranjaHorariaDTO franjaHorariaDTO2 = new FranjaHorariaDTO();
        assertThat(franjaHorariaDTO1).isNotEqualTo(franjaHorariaDTO2);
        franjaHorariaDTO2.setId(franjaHorariaDTO1.getId());
        assertThat(franjaHorariaDTO1).isEqualTo(franjaHorariaDTO2);
        franjaHorariaDTO2.setId(2L);
        assertThat(franjaHorariaDTO1).isNotEqualTo(franjaHorariaDTO2);
        franjaHorariaDTO1.setId(null);
        assertThat(franjaHorariaDTO1).isNotEqualTo(franjaHorariaDTO2);
    }
}

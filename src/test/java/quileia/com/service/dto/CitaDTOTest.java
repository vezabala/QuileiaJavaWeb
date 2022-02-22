package quileia.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class CitaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CitaDTO.class);
        CitaDTO citaDTO1 = new CitaDTO();
        citaDTO1.setId(1L);
        CitaDTO citaDTO2 = new CitaDTO();
        assertThat(citaDTO1).isNotEqualTo(citaDTO2);
        citaDTO2.setId(citaDTO1.getId());
        assertThat(citaDTO1).isEqualTo(citaDTO2);
        citaDTO2.setId(2L);
        assertThat(citaDTO1).isNotEqualTo(citaDTO2);
        citaDTO1.setId(null);
        assertThat(citaDTO1).isNotEqualTo(citaDTO2);
    }
}

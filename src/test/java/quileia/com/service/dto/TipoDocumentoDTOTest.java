package quileia.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import quileia.com.web.rest.TestUtil;

public class TipoDocumentoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoDocumentoDTO.class);
        TipoDocumentoDTO tipoDocumentoDTO1 = new TipoDocumentoDTO();
        tipoDocumentoDTO1.setId(1L);
        TipoDocumentoDTO tipoDocumentoDTO2 = new TipoDocumentoDTO();
        assertThat(tipoDocumentoDTO1).isNotEqualTo(tipoDocumentoDTO2);
        tipoDocumentoDTO2.setId(tipoDocumentoDTO1.getId());
        assertThat(tipoDocumentoDTO1).isEqualTo(tipoDocumentoDTO2);
        tipoDocumentoDTO2.setId(2L);
        assertThat(tipoDocumentoDTO1).isNotEqualTo(tipoDocumentoDTO2);
        tipoDocumentoDTO1.setId(null);
        assertThat(tipoDocumentoDTO1).isNotEqualTo(tipoDocumentoDTO2);
    }
}

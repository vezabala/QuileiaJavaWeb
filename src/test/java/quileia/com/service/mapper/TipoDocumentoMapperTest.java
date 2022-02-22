package quileia.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoDocumentoMapperTest {

    private TipoDocumentoMapper tipoDocumentoMapper;

    @BeforeEach
    public void setUp() {
        tipoDocumentoMapper = new TipoDocumentoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tipoDocumentoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoDocumentoMapper.fromId(null)).isNull();
    }
}

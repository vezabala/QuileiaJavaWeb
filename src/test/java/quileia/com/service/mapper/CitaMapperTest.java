package quileia.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CitaMapperTest {

    private CitaMapper citaMapper;

    @BeforeEach
    public void setUp() {
        citaMapper = new CitaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(citaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(citaMapper.fromId(null)).isNull();
    }
}

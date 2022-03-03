package quileia.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicoMapperTest {

    private MedicoMapper medicoMapper;

    @BeforeEach
    public void setUp() {
        medicoMapper = new MedicoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicoMapper.fromId(null)).isNull();
    }
}

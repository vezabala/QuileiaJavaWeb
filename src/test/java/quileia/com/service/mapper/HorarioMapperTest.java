package quileia.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HorarioMapperTest {

    private HorarioMapper horarioMapper;

    @BeforeEach
    public void setUp() {
        horarioMapper = new HorarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(horarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(horarioMapper.fromId(null)).isNull();
    }
}

package quileia.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FranjaHorariaMapperTest {

    private FranjaHorariaMapper franjaHorariaMapper;

    @BeforeEach
    public void setUp() {
        franjaHorariaMapper = new FranjaHorariaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(franjaHorariaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(franjaHorariaMapper.fromId(null)).isNull();
    }
}

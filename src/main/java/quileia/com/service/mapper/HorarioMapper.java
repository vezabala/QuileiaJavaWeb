package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.HorarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Horario} and its DTO {@link HorarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {FranjaHorariaMapper.class})
public interface HorarioMapper extends EntityMapper<HorarioDTO, Horario> {

    @Mapping(source = "franjaHoraria.id", target = "franjaHorariaId")
    @Mapping(source = "franjaHoraria.franja", target = "franjaHorariaFranja")
    HorarioDTO toDto(Horario horario);

    @Mapping(target = "horas", ignore = true)
    @Mapping(target = "removeHoras", ignore = true)
    @Mapping(source = "franjaHorariaId", target = "franjaHoraria")
    Horario toEntity(HorarioDTO horarioDTO);

    default Horario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Horario horario = new Horario();
        horario.setId(id);
        return horario;
    }
}

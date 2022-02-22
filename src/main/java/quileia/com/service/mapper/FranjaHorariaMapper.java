package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.FranjaHorariaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FranjaHoraria} and its DTO {@link FranjaHorariaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FranjaHorariaMapper extends EntityMapper<FranjaHorariaDTO, FranjaHoraria> {


    @Mapping(target = "horarios", ignore = true)
    @Mapping(target = "removeHorarios", ignore = true)
    @Mapping(target = "medicos", ignore = true)
    @Mapping(target = "removeMedicos", ignore = true)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "removeCitas", ignore = true)
    FranjaHoraria toEntity(FranjaHorariaDTO franjaHorariaDTO);

    default FranjaHoraria fromId(Long id) {
        if (id == null) {
            return null;
        }
        FranjaHoraria franjaHoraria = new FranjaHoraria();
        franjaHoraria.setId(id);
        return franjaHoraria;
    }
}

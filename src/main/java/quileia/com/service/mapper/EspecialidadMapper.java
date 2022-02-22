package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.EspecialidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Especialidad} and its DTO {@link EspecialidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EspecialidadMapper extends EntityMapper<EspecialidadDTO, Especialidad> {


    @Mapping(target = "medicos", ignore = true)
    @Mapping(target = "removeMedicos", ignore = true)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "removeCitas", ignore = true)
    Especialidad toEntity(EspecialidadDTO especialidadDTO);

    default Especialidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Especialidad especialidad = new Especialidad();
        especialidad.setId(id);
        return especialidad;
    }
}

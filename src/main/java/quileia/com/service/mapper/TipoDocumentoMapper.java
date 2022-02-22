package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.TipoDocumentoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoDocumento} and its DTO {@link TipoDocumentoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoDocumentoMapper extends EntityMapper<TipoDocumentoDTO, TipoDocumento> {


    @Mapping(target = "medicos", ignore = true)
    @Mapping(target = "removeMedicos", ignore = true)
    @Mapping(target = "pacientes", ignore = true)
    @Mapping(target = "removePacientes", ignore = true)
    TipoDocumento toEntity(TipoDocumentoDTO tipoDocumentoDTO);

    default TipoDocumento fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(id);
        return tipoDocumento;
    }
}

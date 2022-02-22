package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.PacienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paciente} and its DTO {@link PacienteDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoDocumentoMapper.class})
public interface PacienteMapper extends EntityMapper<PacienteDTO, Paciente> {

    @Mapping(source = "tipoDocumento.id", target = "tipoDocumentoId")
    @Mapping(source = "tipoDocumento.nombreDocumento", target = "tipoDocumentoNombreDocumento")
    PacienteDTO toDto(Paciente paciente);

    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "removeCitas", ignore = true)
    @Mapping(source = "tipoDocumentoId", target = "tipoDocumento")
    Paciente toEntity(PacienteDTO pacienteDTO);

    default Paciente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setId(id);
        return paciente;
    }
}

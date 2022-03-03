package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.MedicoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Medico} and its DTO {@link MedicoDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoDocumentoMapper.class, EspecialidadMapper.class, FranjaHorariaMapper.class})
public interface MedicoMapper extends EntityMapper<MedicoDTO, Medico> {

    @Mapping(source = "tipoDocumento.id", target = "tipoDocumentoId")
    @Mapping(source = "tipoDocumento.nombreDocumento", target = "tipoDocumentoNombreDocumento")
    @Mapping(source = "especialidad.id", target = "especialidadId")
    @Mapping(source = "especialidad.nombreEspecialidad", target = "especialidadNombreEspecialidad")
    @Mapping(source = "franjaHoraria.id", target = "franjaHorariaId")
    @Mapping(source = "franjaHoraria.franja", target = "franjaHorariaFranja")
    MedicoDTO toDto(Medico medico);

    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "removeCitas", ignore = true)
    @Mapping(source = "tipoDocumentoId", target = "tipoDocumento")
    @Mapping(source = "especialidadId", target = "especialidad")
    @Mapping(source = "franjaHorariaId", target = "franjaHoraria")
    Medico toEntity(MedicoDTO medicoDTO);

    default Medico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Medico medico = new Medico();
        medico.setId(id);
        return medico;
    }
}

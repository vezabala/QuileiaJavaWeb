package quileia.com.service.mapper;


import quileia.com.domain.*;
import quileia.com.service.dto.CitaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cita} and its DTO {@link CitaDTO}.
 */
@Mapper(componentModel = "spring", uses = {EspecialidadMapper.class, FranjaHorariaMapper.class, HorarioMapper.class, MedicoMapper.class, PacienteMapper.class})
public interface CitaMapper extends EntityMapper<CitaDTO, Cita> {

    @Mapping(source = "especialidad.id", target = "especialidadId")
    @Mapping(source = "especialidad.nombreEspecialidad", target = "especialidadNombreEspecialidad")
    @Mapping(source = "franjaHoraria.id", target = "franjaHorariaId")
    @Mapping(source = "franjaHoraria.franja", target = "franjaHorariaFranja")
    @Mapping(source = "horario.id", target = "horarioId")
    @Mapping(source = "horario.hora", target = "horarioHora")
    @Mapping(source = "medicos.id", target = "medicosId")
    @Mapping(source = "medicos.nombreCompleto", target = "medicosNombreCompleto")
    @Mapping(source = "pacientes.id", target = "pacientesId")
    @Mapping(source = "pacientes.nombreCompleto", target = "pacientesNombreCompleto")
    CitaDTO toDto(Cita cita);

    @Mapping(source = "especialidadId", target = "especialidad")
    @Mapping(source = "franjaHorariaId", target = "franjaHoraria")
    @Mapping(source = "horarioId", target = "horario")
    @Mapping(source = "medicosId", target = "medicos")
    @Mapping(source = "pacientesId", target = "pacientes")
    Cita toEntity(CitaDTO citaDTO);

    default Cita fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cita cita = new Cita();
        cita.setId(id);
        return cita;
    }
}

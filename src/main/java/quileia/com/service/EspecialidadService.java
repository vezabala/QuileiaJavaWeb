package quileia.com.service;

import quileia.com.domain.Especialidad;
import quileia.com.domain.enumeration.Estado;
import quileia.com.repository.EspecialidadRepository;
import quileia.com.service.dto.EspecialidadDTO;
import quileia.com.service.mapper.EspecialidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Especialidad}.
 */
@Service
@Transactional
public class EspecialidadService {

    private final Logger log = LoggerFactory.getLogger(EspecialidadService.class);

    private final EspecialidadRepository especialidadRepository;

    private final EspecialidadMapper especialidadMapper;

    public EspecialidadService(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    /**
     * Save a especialidad.
     *
     * @param especialidadDTO the entity to save.
     * @return the persisted entity.
     */
    public EspecialidadDTO save(EspecialidadDTO especialidadDTO) {
        log.debug("Request to save Especialidad : {}", especialidadDTO);
        Especialidad especialidad = especialidadMapper.toEntity(especialidadDTO);
        especialidad = especialidadRepository.save(especialidad);
        return especialidadMapper.toDto(especialidad);
    }

    /**
     * Get all the especialidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EspecialidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Especialidads");
        return especialidadRepository.findAll(pageable)
            .map(especialidadMapper::toDto);
    }

    /**
     * Get all the especialidades by Estado.
     *
     * @param estado estado to search
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EspecialidadDTO> findByEstado(String estado){
        log.debug("Request to get all Especialidad by estado");
        return especialidadMapper.toDto(especialidadRepository.findByEstadoEspecialidad(Estado.valueOf(estado)));
    }

    /**
     * Get one especialidad by nombre especialidad.
     *
     * @param nombreEspecialidad the nombre documento of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<EspecialidadDTO> findByNombreEspecialidad(String nombreEspecialidad) {
        log.debug("Request to get especialidad : {}", nombreEspecialidad);
        return especialidadRepository.findByNombreEspecialidad(nombreEspecialidad)
            .map(especialidadMapper::toDto);
    }

    /**
     * Get one especialidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EspecialidadDTO> findOne(Long id) {
        log.debug("Request to get Especialidad : {}", id);
        return especialidadRepository.findById(id)
            .map(especialidadMapper::toDto);
    }

    /**
     * Delete the especialidad by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Especialidad : {}", id);
        especialidadRepository.deleteById(id);
    }
}

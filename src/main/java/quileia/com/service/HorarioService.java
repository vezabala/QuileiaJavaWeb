package quileia.com.service;

import quileia.com.domain.Horario;
import quileia.com.repository.HorarioRepository;
import quileia.com.service.dto.HorarioDTO;
import quileia.com.service.mapper.HorarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Horario}.
 */
@Service
@Transactional
public class HorarioService {

    private final Logger log = LoggerFactory.getLogger(HorarioService.class);

    private final HorarioRepository horarioRepository;

    private final HorarioMapper horarioMapper;

    public HorarioService(HorarioRepository horarioRepository, HorarioMapper horarioMapper) {
        this.horarioRepository = horarioRepository;
        this.horarioMapper = horarioMapper;
    }

    /**
     * Save a horario.
     *
     * @param horarioDTO the entity to save.
     * @return the persisted entity.
     */
    public HorarioDTO save(HorarioDTO horarioDTO) {
        log.debug("Request to save Horario : {}", horarioDTO);
        Horario horario = horarioMapper.toEntity(horarioDTO);
        horario = horarioRepository.save(horario);
        return horarioMapper.toDto(horario);
    }

    /**
     * Get all the horarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HorarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Horarios");
        return horarioRepository.findAll(pageable)
            .map(horarioMapper::toDto);
    }

    /**
     * Get one Horario by hora.
     *
     * @param hora the nombre documento of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<HorarioDTO> findByHora(String hora) {
        log.debug("Request to get Horario : {}", hora);
        return horarioRepository.findByHora(hora)
            .map(horarioMapper::toDto);
    }

    /**
     * Get one horario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HorarioDTO> findOne(Long id) {
        log.debug("Request to get Horario : {}", id);
        return horarioRepository.findById(id)
            .map(horarioMapper::toDto);
    }

    /**
     * Delete the horario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Horario : {}", id);
        horarioRepository.deleteById(id);
    }
}

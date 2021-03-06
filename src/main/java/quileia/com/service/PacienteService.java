package quileia.com.service;

import quileia.com.domain.Paciente;
import quileia.com.repository.PacienteRepository;
import quileia.com.service.dto.PacienteDTO;
import quileia.com.service.mapper.PacienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Paciente}.
 */
@Service
@Transactional
public class PacienteService {

    private final Logger log = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;

    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    /**
     * Save a paciente.
     *
     * @param pacienteDTO the entity to save.
     * @return the persisted entity.
     */
    public PacienteDTO save(PacienteDTO pacienteDTO) {
        log.debug("Request to save Paciente : {}", pacienteDTO);
        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDto(paciente);
    }

    /**
     * Get all the pacientes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PacienteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pacientes");
        return pacienteRepository.findAll(pageable)
            .map(pacienteMapper::toDto);
    }

    /**
     * Get one paciente by tipo documento and identificacion.
     *
     * @param pacienteDTO the DTO of the paciente
     * @return the optional with the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PacienteDTO> findByIdentificacionAndTipoDocumento(PacienteDTO pacienteDTO) {
        log.debug("Request to get Paciente : {}", pacienteDTO.getId()+", "+pacienteDTO.getTipoDocumentoId());
        return pacienteRepository.findByIdentificacionAndTipoDocumento(pacienteDTO.getIdentificacion(),
            pacienteMapper.toEntity(pacienteDTO).getTipoDocumento())
            .map(pacienteMapper::toDto);
    }

    /**
     * Get one paciente by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PacienteDTO> findOne(Long id) {
        log.debug("Request to get Paciente : {}", id);
        return pacienteRepository.findById(id)
            .map(pacienteMapper::toDto);
    }

    /**
     * Delete the paciente by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Paciente : {}", id);
        pacienteRepository.deleteById(id);
    }
    /**
     * Get Medico By list
     * @return List of medico
     */
    public List<Paciente> findAllList(){
        return pacienteRepository.findAll();
    }
}

package quileia.com.service;

import quileia.com.domain.Cita;
import quileia.com.repository.CitaRepository;
import quileia.com.service.dto.CitaDTO;
import quileia.com.service.mapper.CitaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cita}.
 */
@Service
@Transactional
public class CitaService {

    private final Logger log = LoggerFactory.getLogger(CitaService.class);

    private final CitaRepository citaRepository;

    private final CitaMapper citaMapper;

    public CitaService(CitaRepository citaRepository, CitaMapper citaMapper) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
    }

    /**
     * Save a cita.
     *
     * @param citaDTO the entity to save.
     * @return the persisted entity.
     */
    public CitaDTO save(CitaDTO citaDTO) {
        log.debug("Request to save Cita : {}", citaDTO);
        Cita cita = citaMapper.toEntity(citaDTO);
        cita = citaRepository.save(cita);
        return citaMapper.toDto(cita);
    }

    /**
     * Get all the citas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CitaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Citas");
        return citaRepository.findAll(pageable)
            .map(citaMapper::toDto);
    }

    /**
     * get one cina by medico and horario
     * @param citaDTO the DTO of medio
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CitaDTO> findByMedicosAndHorarioAndFecha(CitaDTO citaDTO) {
        log.debug("Request to get Cita : {}", citaDTO.getHorarioId() + ", "
            + citaDTO.getMedicosId() + ", "
            + citaDTO.getId());
        return citaRepository.findByMedicosAndHorarioAndFecha(
            citaMapper.toEntity(citaDTO).getMedicos(),
            citaMapper.toEntity(citaDTO).getHorario(),
            citaDTO.getFecha())
            .map(citaMapper::toDto);
    }
    /**
     * get one cina by paciente and horario
     * @param citaDTO the DTO of medio
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CitaDTO> findByPacientesAndHorarioAndFecha(CitaDTO citaDTO) {
        log.debug("Request to get Cita : {}", citaDTO.getHorarioId() + ", "
            + citaDTO.getPacientesId() + ", "
            + citaDTO.getId());
        return citaRepository.findByPacientesAndHorarioAndFecha(
            citaMapper.toEntity(citaDTO).getPacientes(),
            citaMapper.toEntity(citaDTO).getHorario(),
            citaDTO.getFecha())
            .map(citaMapper::toDto);
    }
    /**
     * get one cina by paciente and medico
     * @param citaDTO the DTO of medio
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CitaDTO> findByPacientesAndMedicos(CitaDTO citaDTO) {
        log.debug("Request to get Cita : {}", citaDTO.getMedicosId() + ", "
            + citaDTO.getPacientesId());
        return citaRepository.findByPacientesAndMedicos(
            citaMapper.toEntity(citaDTO).getPacientes(),
            citaMapper.toEntity(citaDTO).getMedicos())
            .map(citaMapper::toDto);
    }

    /**
     * Get one cita by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CitaDTO> findOne(Long id) {
        log.debug("Request to get Cita : {}", id);
        return citaRepository.findById(id)
            .map(citaMapper::toDto);
    }

    /**
     * Delete the cita by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cita : {}", id);
        citaRepository.deleteById(id);
    }
}

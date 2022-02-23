package quileia.com.service;

import quileia.com.domain.FranjaHoraria;
import quileia.com.domain.enumeration.Estado;
import quileia.com.repository.FranjaHorariaRepository;
import quileia.com.service.dto.FranjaHorariaDTO;
import quileia.com.service.mapper.FranjaHorariaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FranjaHoraria}.
 */
@Service
@Transactional
public class FranjaHorariaService {

    private final Logger log = LoggerFactory.getLogger(FranjaHorariaService.class);

    private final FranjaHorariaRepository franjaHorariaRepository;

    private final FranjaHorariaMapper franjaHorariaMapper;

    public FranjaHorariaService(FranjaHorariaRepository franjaHorariaRepository, FranjaHorariaMapper franjaHorariaMapper) {
        this.franjaHorariaRepository = franjaHorariaRepository;
        this.franjaHorariaMapper = franjaHorariaMapper;
    }

    /**
     * Save a franjaHoraria.
     *
     * @param franjaHorariaDTO the entity to save.
     * @return the persisted entity.
     */
    public FranjaHorariaDTO save(FranjaHorariaDTO franjaHorariaDTO) {
        log.debug("Request to save FranjaHoraria : {}", franjaHorariaDTO);
        FranjaHoraria franjaHoraria = franjaHorariaMapper.toEntity(franjaHorariaDTO);
        franjaHoraria = franjaHorariaRepository.save(franjaHoraria);
        return franjaHorariaMapper.toDto(franjaHoraria);
    }

    /**
     * Get all the franjas horarias by Estado.
     *
     * @param estado estado to search
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FranjaHorariaDTO> findByEstado(String estado){
        log.debug("Request to get all franja horaria by estado");
        return franjaHorariaMapper.toDto(franjaHorariaRepository.findByEstadoFranjaHoraria(Estado.valueOf(estado)));
    }

    /**
     * Get one franja horaria by franja.
     *
     * @param franja the franja horaria of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FranjaHorariaDTO> findByFranja(String franja) {
        log.debug("Request to get franja horaria : {}", franja);
        return franjaHorariaRepository.findByFranja(franja)
            .map(franjaHorariaMapper::toDto);
    }

    /**
     * Get all the franjaHorarias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FranjaHorariaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FranjaHorarias");
        return franjaHorariaRepository.findAll(pageable)
            .map(franjaHorariaMapper::toDto);
    }

    /**
     * Get one franjaHoraria by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FranjaHorariaDTO> findOne(Long id) {
        log.debug("Request to get FranjaHoraria : {}", id);
        return franjaHorariaRepository.findById(id)
            .map(franjaHorariaMapper::toDto);
    }

    /**
     * Delete the franjaHoraria by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FranjaHoraria : {}", id);
        franjaHorariaRepository.deleteById(id);
    }
}

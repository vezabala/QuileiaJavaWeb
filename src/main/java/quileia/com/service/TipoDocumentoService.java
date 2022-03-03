package quileia.com.service;

import quileia.com.domain.TipoDocumento;
import quileia.com.domain.enumeration.Estado;
import quileia.com.repository.TipoDocumentoRepository;
import quileia.com.service.dto.TipoDocumentoDTO;
import quileia.com.service.mapper.TipoDocumentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoDocumento}.
 */
@Service
@Transactional
public class TipoDocumentoService {

    private final Logger log = LoggerFactory.getLogger(TipoDocumentoService.class);

    private final TipoDocumentoRepository tipoDocumentoRepository;

    private final TipoDocumentoMapper tipoDocumentoMapper;

    public TipoDocumentoService(TipoDocumentoRepository tipoDocumentoRepository, TipoDocumentoMapper tipoDocumentoMapper) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoDocumentoMapper = tipoDocumentoMapper;
    }

    /**
     * Save a tipoDocumento.
     *
     * @param tipoDocumentoDTO the entity to save.
     * @return the persisted entity.
     */
    public TipoDocumentoDTO save(TipoDocumentoDTO tipoDocumentoDTO) {
        log.debug("Request to save TipoDocumento : {}", tipoDocumentoDTO);
        TipoDocumento tipoDocumento = tipoDocumentoMapper.toEntity(tipoDocumentoDTO);
        tipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
        return tipoDocumentoMapper.toDto(tipoDocumento);
    }

    /**
     * Get all the tipoDocumentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoDocumentoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoDocumentos");
        return tipoDocumentoRepository.findAll(pageable)
            .map(tipoDocumentoMapper::toDto);
    }

    /**
     * Get all the tipoDocumentos by Estado.
     *
     * @param estado estado to search
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TipoDocumentoDTO> findByEstado(String estado){
        log.debug("Request to get all TipoDocumentos by estado");
        return tipoDocumentoMapper.toDto(tipoDocumentoRepository.findByEstadoTipoDocumento(Estado.valueOf(estado)));
    }

    /**
     * Get one tipoDocumento by iniciales.
     *
     * @param iniciales the iniciales of the entity.
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TipoDocumentoDTO> findByIniciales(String iniciales) {
        log.debug("Request to get TipoSocumento1 : {}", iniciales);
        return tipoDocumentoRepository.findByIniciales(iniciales)
            .map(tipoDocumentoMapper::toDto);
    }

    /**
     * Get one tipoDocumento by nombre documento.
     *
     * @param nombreDocumento the nombre documento of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TipoDocumentoDTO> findByNombreDocumento(String nombreDocumento) {
        log.debug("Request to get TipoDocumento : {}", nombreDocumento);
        return tipoDocumentoRepository.findByNombreDocumento(nombreDocumento)
            .map(tipoDocumentoMapper::toDto);
    }

    /**
     * Get one tipoDocumento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TipoDocumentoDTO> findOne(Long id) {
        log.debug("Request to get TipoDocumento : {}", id);
        return tipoDocumentoRepository.findById(id)
            .map(tipoDocumentoMapper::toDto);
    }

    /**
     * Delete the tipoDocumento by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoDocumento : {}", id);
        tipoDocumentoRepository.deleteById(id);
    }
}

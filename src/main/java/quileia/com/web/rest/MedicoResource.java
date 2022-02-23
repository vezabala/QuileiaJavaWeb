package quileia.com.web.rest;

import quileia.com.service.MedicoService;
import quileia.com.web.rest.errors.BadRequestAlertException;
import quileia.com.service.dto.MedicoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link quileia.com.domain.Medico}.
 */
@RestController
@RequestMapping("/api")
public class MedicoResource {

    private final Logger log = LoggerFactory.getLogger(MedicoResource.class);

    private static final String ENTITY_NAME = "medico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicoService medicoService;

    public MedicoResource(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    /**
     * {@code POST  /medicos} : Create a new medico.
     *
     * @param medicoDTO the medicoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicoDTO, or with status {@code 400 (Bad Request)} if the medico has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medicos")
    public ResponseEntity<MedicoDTO> createMedico(@Valid @RequestBody MedicoDTO medicoDTO) throws URISyntaxException {
        log.debug("REST request to save Medico : {}", medicoDTO);
        if (medicoDTO.getId() != null) {
            throw new BadRequestAlertException("A new medico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (medicoService.findByIdentificacionAndTipoDocumento(medicoDTO).isPresent()) {
            throw new BadRequestAlertException("A new medico cannot already have an NUMERO DOCUMENTO and TIPO DOCUMENTO", ENTITY_NAME, "idmedicoexists");
        }
        if (medicoService.findByIdentificacionAndFranjaHoraria(medicoDTO).isPresent()) {
            throw new BadRequestAlertException("A new medico cannot already have an NUMERO DOCUMENTO and FRANJA HORARIA", ENTITY_NAME, "idmedicofranjaexists");
        }
        MedicoDTO result = medicoService.save(medicoDTO);
        return ResponseEntity.created(new URI("/api/medicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medicos} : Updates an existing medico.
     *
     * @param medicoDTO the medicoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicoDTO,
     * or with status {@code 400 (Bad Request)} if the medicoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medicos")
    public ResponseEntity<MedicoDTO> updateMedico(@Valid @RequestBody MedicoDTO medicoDTO) throws URISyntaxException {
        log.debug("REST request to update Medico : {}", medicoDTO);
        if (medicoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<MedicoDTO> medicoTemp = medicoService.findByIdentificacionAndTipoDocumento(medicoDTO);
        if (medicoTemp.isPresent() && (!medicoTemp.get().getId().equals(medicoDTO.getId()))) {
            throw new BadRequestAlertException("A new medico cannot already have an NUMERO DOCUMENTO and TIPO DOCUMENTO", ENTITY_NAME, "idmedicoexists");
        }
        Optional<MedicoDTO> medicoTemp2 = medicoService.findByIdentificacionAndFranjaHoraria(medicoDTO);
        if (medicoTemp.isPresent() && (!medicoTemp.get().getId().equals(medicoDTO.getId()))) {
            throw new BadRequestAlertException("A new medico cannot already have an NUMERO DOCUMENTO and FRANJA HORARIA", ENTITY_NAME, "idmedicofranjaexists");
        }
        MedicoDTO result = medicoService.save(medicoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medicos} : get all the medicos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicos in body.
     */
    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoDTO>> getAllMedicos(Pageable pageable) {
        log.debug("REST request to get a page of Medicos");
        Page<MedicoDTO> page = medicoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medicos/:id} : get the "id" medico.
     *
     * @param id the id of the medicoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medicos/{id}")
    public ResponseEntity<MedicoDTO> getMedico(@PathVariable Long id) {
        log.debug("REST request to get Medico : {}", id);
        Optional<MedicoDTO> medicoDTO = medicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicoDTO);
    }

    /**
     * {@code DELETE  /medicos/:id} : delete the "id" medico.
     *
     * @param id the id of the medicoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medicos/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        log.debug("REST request to delete Medico : {}", id);
        medicoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package quileia.com.web.rest;

import quileia.com.service.EspecialidadService;
import quileia.com.web.rest.errors.BadRequestAlertException;
import quileia.com.service.dto.EspecialidadDTO;

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
 * REST controller for managing {@link quileia.com.domain.Especialidad}.
 */
@RestController
@RequestMapping("/api")
public class EspecialidadResource {

    private final Logger log = LoggerFactory.getLogger(EspecialidadResource.class);

    private static final String ENTITY_NAME = "especialidad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EspecialidadService especialidadService;

    public EspecialidadResource(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    /**
     * {@code POST  /especialidads} : Create a new especialidad.
     *
     * @param especialidadDTO the especialidadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new especialidadDTO, or with status {@code 400 (Bad Request)} if the especialidad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/especialidads")
    public ResponseEntity<EspecialidadDTO> createEspecialidad(@Valid @RequestBody EspecialidadDTO especialidadDTO) throws URISyntaxException {
        log.debug("REST request to save Especialidad : {}", especialidadDTO);
        if (especialidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new especialidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (especialidadService.findByNombreEspecialidad(especialidadDTO.getNombreEspecialidad()).isPresent()) {
            throw new BadRequestAlertException("A new especialidad cannot already have an NOMBREESPECIALIDAD", ENTITY_NAME, "nombreespecialistaexists");
        }
        EspecialidadDTO result = especialidadService.save(especialidadDTO);
        return ResponseEntity.created(new URI("/api/especialidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /especialidads} : Updates an existing especialidad.
     *
     * @param especialidadDTO the especialidadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated especialidadDTO,
     * or with status {@code 400 (Bad Request)} if the especialidadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the especialidadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/especialidads")
    public ResponseEntity<EspecialidadDTO> updateEspecialidad(@Valid @RequestBody EspecialidadDTO especialidadDTO) throws URISyntaxException {
        log.debug("REST request to update Especialidad : {}", especialidadDTO);
        if (especialidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<EspecialidadDTO> especialidadTemp = especialidadService.findByNombreEspecialidad(especialidadDTO.getNombreEspecialidad());
        if (especialidadTemp.isPresent() && !especialidadTemp.get().getId().equals(especialidadDTO.getId())) {
            throw new BadRequestAlertException("A new especialidad cannot already have an NOMBREESPECIALIDAD", ENTITY_NAME, "nombreespecialistaexists");
        }
        EspecialidadDTO result = especialidadService.save(especialidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, especialidadDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /especialidads} : get all the especialidads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of especialidads in body.
     */
    @GetMapping("/especialidads")
    public ResponseEntity<List<EspecialidadDTO>> getAllEspecialidads(Pageable pageable) {
        log.debug("REST request to get a page of Especialidads");
        Page<EspecialidadDTO> page = especialidadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * Get all the especialidad by estado.
     *
     * @param estado estado to search
     * @return the list of entities.
     */
    @GetMapping("/especialidad/estado/{estado}")
    public ResponseEntity<List<EspecialidadDTO>> getAllEspecialidadesByEstado(@PathVariable String estado) {
        log.debug("REST request to get a page of Especialidades");
        List<EspecialidadDTO> page = especialidadService.findByEstado(estado);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /especialidads/:id} : get the "id" especialidad.
     *
     * @param id the id of the especialidadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the especialidadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/especialidads/{id}")
    public ResponseEntity<EspecialidadDTO> getEspecialidad(@PathVariable Long id) {
        log.debug("REST request to get Especialidad : {}", id);
        Optional<EspecialidadDTO> especialidadDTO = especialidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(especialidadDTO);
    }

    /**
     * {@code DELETE  /especialidads/:id} : delete the "id" especialidad.
     *
     * @param id the id of the especialidadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/especialidads/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Long id) {
        log.debug("REST request to delete Especialidad : {}", id);
        especialidadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

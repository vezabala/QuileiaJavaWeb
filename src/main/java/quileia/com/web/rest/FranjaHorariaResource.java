package quileia.com.web.rest;

import quileia.com.service.FranjaHorariaService;
import quileia.com.web.rest.errors.BadRequestAlertException;
import quileia.com.service.dto.FranjaHorariaDTO;

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
 * REST controller for managing {@link quileia.com.domain.FranjaHoraria}.
 */
@RestController
@RequestMapping("/api")
public class FranjaHorariaResource {

    private final Logger log = LoggerFactory.getLogger(FranjaHorariaResource.class);

    private static final String ENTITY_NAME = "franjaHoraria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FranjaHorariaService franjaHorariaService;

    public FranjaHorariaResource(FranjaHorariaService franjaHorariaService) {
        this.franjaHorariaService = franjaHorariaService;
    }

    /**
     * {@code POST  /franja-horarias} : Create a new franjaHoraria.
     *
     * @param franjaHorariaDTO the franjaHorariaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new franjaHorariaDTO, or with status {@code 400 (Bad Request)} if the franjaHoraria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/franja-horarias")
    public ResponseEntity<FranjaHorariaDTO> createFranjaHoraria(@Valid @RequestBody FranjaHorariaDTO franjaHorariaDTO) throws URISyntaxException {
        log.debug("REST request to save FranjaHoraria : {}", franjaHorariaDTO);
        if (franjaHorariaDTO.getId() != null) {
            throw new BadRequestAlertException("A new franjaHoraria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FranjaHorariaDTO result = franjaHorariaService.save(franjaHorariaDTO);
        return ResponseEntity.created(new URI("/api/franja-horarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /franja-horarias} : Updates an existing franjaHoraria.
     *
     * @param franjaHorariaDTO the franjaHorariaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated franjaHorariaDTO,
     * or with status {@code 400 (Bad Request)} if the franjaHorariaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the franjaHorariaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/franja-horarias")
    public ResponseEntity<FranjaHorariaDTO> updateFranjaHoraria(@Valid @RequestBody FranjaHorariaDTO franjaHorariaDTO) throws URISyntaxException {
        log.debug("REST request to update FranjaHoraria : {}", franjaHorariaDTO);
        if (franjaHorariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FranjaHorariaDTO result = franjaHorariaService.save(franjaHorariaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, franjaHorariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /franja-horarias} : get all the franjaHorarias.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of franjaHorarias in body.
     */
    @GetMapping("/franja-horarias")
    public ResponseEntity<List<FranjaHorariaDTO>> getAllFranjaHorarias(Pageable pageable) {
        log.debug("REST request to get a page of FranjaHorarias");
        Page<FranjaHorariaDTO> page = franjaHorariaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /franja-horarias/:id} : get the "id" franjaHoraria.
     *
     * @param id the id of the franjaHorariaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the franjaHorariaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/franja-horarias/{id}")
    public ResponseEntity<FranjaHorariaDTO> getFranjaHoraria(@PathVariable Long id) {
        log.debug("REST request to get FranjaHoraria : {}", id);
        Optional<FranjaHorariaDTO> franjaHorariaDTO = franjaHorariaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(franjaHorariaDTO);
    }

    /**
     * {@code DELETE  /franja-horarias/:id} : delete the "id" franjaHoraria.
     *
     * @param id the id of the franjaHorariaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/franja-horarias/{id}")
    public ResponseEntity<Void> deleteFranjaHoraria(@PathVariable Long id) {
        log.debug("REST request to delete FranjaHoraria : {}", id);
        franjaHorariaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package quileia.com.web.rest;

import io.github.jhipster.service.filter.StringFilter;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpStatus;
import quileia.com.Criteria.PacienteCriteria;
import quileia.com.domain.Paciente;
import quileia.com.service.PacienteService;
import quileia.com.service.PacienteServiceQuery;
import quileia.com.service.dto.BusquedaPacienteDTO;
import quileia.com.web.rest.errors.BadRequestAlertException;
import quileia.com.service.dto.PacienteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link quileia.com.domain.Paciente}.
 */
@RestController
@RequestMapping("/api")
public class PacienteResource {

    private final Logger log = LoggerFactory.getLogger(PacienteResource.class);

    private static final String ENTITY_NAME = "paciente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PacienteService pacienteService;

    private final PacienteServiceQuery pacienteServiceQuery;

    public PacienteResource(PacienteService pacienteService, PacienteServiceQuery pacienteServiceQuery) {
        this.pacienteService = pacienteService;
        this.pacienteServiceQuery = pacienteServiceQuery;
    }

    /**
     * {@code POST  /pacientes} : Create a new paciente.
     *
     * @param pacienteDTO the pacienteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pacienteDTO, or with status {@code 400 (Bad Request)} if the paciente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pacientes")
    public ResponseEntity<PacienteDTO> createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) throws URISyntaxException {
        log.debug("REST request to save Paciente : {}", pacienteDTO);
        if (pacienteDTO.getId() != null) {
            throw new BadRequestAlertException("A new paciente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (pacienteService.findByIdentificacionAndTipoDocumento(pacienteDTO).isPresent()) {
            throw new BadRequestAlertException("A new paciente cannot already have an NUMERO DOCUMENTO and TIPO DOCUMENTO", ENTITY_NAME, "idpacienteexists");
        }
        PacienteDTO result = pacienteService.save(pacienteDTO);
        return ResponseEntity.created(new URI("/api/pacientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pacientes} : Updates an existing paciente.
     *
     * @param pacienteDTO the pacienteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pacienteDTO,
     * or with status {@code 400 (Bad Request)} if the pacienteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pacienteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pacientes")
    public ResponseEntity<PacienteDTO> updatePaciente(@Valid @RequestBody PacienteDTO pacienteDTO) throws URISyntaxException {
        log.debug("REST request to update Paciente : {}", pacienteDTO);
        if (pacienteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<PacienteDTO> pacienteTemp = pacienteService.findByIdentificacionAndTipoDocumento(pacienteDTO);
        if (pacienteTemp.isPresent() && (!pacienteTemp.get().getId().equals(pacienteDTO.getId()))) {
            throw new BadRequestAlertException("A new paciente cannot already have an NUMERO DOCUMENTO and TIPO DOCUMENTO", ENTITY_NAME, "idpacienteexists");
        }
        PacienteDTO result = pacienteService.save(pacienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pacienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pacientes} : get all the pacientes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pacientes in body.
     */
    @GetMapping("/pacientes")
    public ResponseEntity<List<PacienteDTO>> getAllPacientes(Pageable pageable) {
        log.debug("REST request to get a page of Pacientes");
        Page<PacienteDTO> page = pacienteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pacientes/:id} : get the "id" paciente.
     *
     * @param id the id of the pacienteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pacienteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<PacienteDTO> getPaciente(@PathVariable Long id) {
        log.debug("REST request to get Paciente : {}", id);
        Optional<PacienteDTO> pacienteDTO = pacienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pacienteDTO);
    }

    /**
     * {@code DELETE  /pacientes/:id} : delete the "id" paciente.
     *
     * @param id the id of the pacienteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        log.debug("REST request to delete Paciente : {}", id);
        pacienteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/pacientes/list")
    public ResponseEntity<List<Paciente>> List(@RequestBody BusquedaPacienteDTO busquedaPacienteDTO){
        PacienteCriteria pacienteCriteria = createCriteriaPaciente(busquedaPacienteDTO);
        List<Paciente> list = pacienteServiceQuery.findByCriterialPaciente(pacienteCriteria);
        return new ResponseEntity<List<Paciente>>(list, HttpStatus.OK);
    }

    private PacienteCriteria createCriteriaPaciente(BusquedaPacienteDTO busquedaPacienteDTO){
        PacienteCriteria pacienteCriteria = new PacienteCriteria();
        if(busquedaPacienteDTO!=null){
            if(!StringUtils.isBlank(busquedaPacienteDTO.getNombreCompleto())){
                StringFilter filterPacienteNombreComp = new StringFilter();
                filterPacienteNombreComp.setEquals(busquedaPacienteDTO.getNombreCompleto());
                pacienteCriteria.setNombreCompleto(filterPacienteNombreComp);
            }
            if(!StringUtils.isBlank(busquedaPacienteDTO.getIdentificacion())){
                StringFilter filterPacienteIdentificacion = new StringFilter();
                filterPacienteIdentificacion.setEquals(busquedaPacienteDTO.getIdentificacion());
                pacienteCriteria.setIdentificacion(filterPacienteIdentificacion);
            }
        }
        return pacienteCriteria;
    }

    @GetMapping("/pacientes/list")
    public ResponseEntity<List<Paciente>> list(){
        List<Paciente> list = pacienteService.findAllList();
        return new ResponseEntity<List<Paciente>>(list, HttpStatus.OK);
    }
}

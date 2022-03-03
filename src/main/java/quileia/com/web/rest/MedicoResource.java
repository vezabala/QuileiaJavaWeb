package quileia.com.web.rest;

import io.github.jhipster.service.filter.StringFilter;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpStatus;
import quileia.com.Criteria.CitaCriteria;
import quileia.com.Criteria.MedicoCriteria;
import quileia.com.domain.Cita;
import quileia.com.domain.Medico;
import quileia.com.service.CitaService;
import quileia.com.service.CitaServiceQuery;
import quileia.com.service.MedicoService;
import quileia.com.service.MedicoServiceQuery;
import quileia.com.service.dto.BusquedaCitaDTO;
import quileia.com.service.dto.BusquedaMedicoDto;
import quileia.com.service.dto.CitaDTO;
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
@RequestMapping("/apis")
public class MedicoResource {

    private final Logger log = LoggerFactory.getLogger(MedicoResource.class);

    private static final String ENTITY_NAME = "medico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicoService medicoService;
    private final CitaService citaService;
    private final MedicoServiceQuery medicoServiceQuery;
    private final CitaServiceQuery citaServiceQuery;

    public MedicoResource(MedicoService medicoService, CitaService citaService, MedicoServiceQuery medicoServiceQuery, CitaServiceQuery citaServiceQuery) {
        this.medicoService = medicoService;
        this.citaService = citaService;
        this.medicoServiceQuery = medicoServiceQuery;
        this.citaServiceQuery = citaServiceQuery;
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
        if (medicoTemp2.isPresent() && (!medicoTemp2.get().getId().equals(medicoDTO.getId()))) {
            throw new BadRequestAlertException("A new medico cannot already have an NUMERO DOCUMENTO and FRANJA HORARIA", ENTITY_NAME, "idmedicofranjaexists");
        }
        if (medicoDTO.getEspecialidadId() != null) {
            CitaCriteria citaCriteria = createCriteriaCita(medicoDTO);
            List<Cita> listCita = citaServiceQuery.findByCriterialCita(citaCriteria);
            Optional<MedicoDTO> medicoTemp3 = medicoService.findOne(medicoDTO.getId());
            if(listCita.size() != 0) {
                Long idMedicoDto = medicoTemp3.get().getId();
                Long idMedicoList = listCita.get(0).getMedicos().getId();
                boolean medicoidvalidacion = idMedicoDto == idMedicoList ;
                if(!medicoidvalidacion){
                    medicoidvalidacion = true;
                }
                if (medicoidvalidacion && medicoDTO.getEspecialidadId() != listCita.get(0).getEspecialidad().getId()) {
                    throw new BadRequestAlertException("A new medico cannot already have diferent ESPECIALIDAD", ENTITY_NAME, "idmedicoESPEcitaexist1");
                }
            }
        }
        MedicoDTO result = medicoService.save(medicoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicoDTO.getId().toString()))
            .body(result);
    }

    /**
     * parametro de busqueda
     *
     * @param medicoDTO the medicoDTO
     * @return citaCriteria
     */
    private CitaCriteria createCriteriaCita(MedicoDTO medicoDTO){
        CitaCriteria citaCriteria = new CitaCriteria();
        if(medicoDTO!=null){

            if(!StringUtils.isBlank(medicoDTO.getNombreCompleto())){
                StringFilter filterCitaMedico = new StringFilter();
                filterCitaMedico.setEquals(medicoDTO.getNombreCompleto());
                citaCriteria.setMedico(filterCitaMedico);
            }
        }
        return citaCriteria;
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

    @PostMapping("/medicos/list")
    public ResponseEntity<List<Medico>> List(@RequestBody BusquedaMedicoDto busquedaMedicoDto){
        MedicoCriteria medicoCriteria = createCriteriaMedico(busquedaMedicoDto);
        List<Medico> list = medicoServiceQuery.findByCriterial(medicoCriteria);
        return new ResponseEntity<List<Medico>>(list, HttpStatus.OK);
    }

    private MedicoCriteria createCriteriaMedico(BusquedaMedicoDto busquedaMedicoDto){
        MedicoCriteria medicoCriteria = new MedicoCriteria();
        if(busquedaMedicoDto!=null){
            if(!StringUtils.isBlank(busquedaMedicoDto.getIdentificacion())){
                StringFilter filterMedicoIdentificacion = new StringFilter();
                filterMedicoIdentificacion.setEquals(busquedaMedicoDto.getIdentificacion());
                medicoCriteria.setIdentificacion(filterMedicoIdentificacion);
            }
            if(!StringUtils.isBlank(busquedaMedicoDto.getNombreCompleto())){
                StringFilter filterMedicoNombreComp = new StringFilter();
                filterMedicoNombreComp.setEquals(busquedaMedicoDto.getNombreCompleto());
                medicoCriteria.setNombreCompleto(filterMedicoNombreComp);
            }
            if(!StringUtils.isBlank(busquedaMedicoDto.getFranjaHoraria())){
                StringFilter filterMedicoFranjaHor = new StringFilter();
                filterMedicoFranjaHor.setEquals(busquedaMedicoDto.getFranjaHoraria());
                medicoCriteria.setFranjaHoraria(filterMedicoFranjaHor);
            }
        }
        return medicoCriteria;
    }

    @GetMapping("/medicos/list")
    public ResponseEntity<List<Medico>> list(){
        List<Medico> list = medicoService.findAllList();
        return new ResponseEntity<List<Medico>>(list, HttpStatus.OK);
    }
}

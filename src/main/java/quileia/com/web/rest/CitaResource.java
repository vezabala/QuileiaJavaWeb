package quileia.com.web.rest;

import io.github.jhipster.service.filter.StringFilter;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpStatus;
import quileia.com.Criteria.CitaCriteria;
import quileia.com.Criteria.HorarioCriteria;
import quileia.com.domain.Cita;
import quileia.com.domain.Horario;
import quileia.com.service.*;
import quileia.com.service.dto.*;
import quileia.com.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link quileia.com.domain.Cita}.
 */
@RestController
@RequestMapping("/api")
public class CitaResource {

    private final Logger log = LoggerFactory.getLogger(CitaResource.class);

    private static final String ENTITY_NAME = "cita";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CitaService citaService;
    private final MedicoService medicoService;
    private final HorarioService horarioService;
    private final CitaServiceQuery citaServiceQuery;
    private final HorarioServiceQuery horarioServiceQuery;
    private final FranjaHorariaService franjaHorariaService;

    public CitaResource(CitaService citaService, MedicoService medicoService, HorarioService horarioService, CitaServiceQuery citaServiceQuery, HorarioServiceQuery horarioServiceQuery, FranjaHorariaService franjaHorariaService) {
        this.citaService = citaService;
        this.medicoService = medicoService;
        this.horarioService = horarioService;
        this.citaServiceQuery = citaServiceQuery;
        this.horarioServiceQuery = horarioServiceQuery;
        this.franjaHorariaService = franjaHorariaService;
    }

    /**
     * {@code POST  /citas} : Create a new cita.
     *
     * @param citaDTO the citaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new citaDTO, or with status {@code 400 (Bad Request)} if the cita has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/citas")
    public ResponseEntity<CitaDTO> createCita(@Valid @RequestBody CitaDTO citaDTO) throws URISyntaxException {
        log.debug("REST request to save Cita : {}", citaDTO);
        if (citaDTO.getId() != null) {
            throw new BadRequestAlertException("A new cita cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (citaService.findByMedicosAndHorarioAndFecha(citaDTO).isPresent()) {
            throw new BadRequestAlertException("A new cita cannot already have a MEDICO and HORARIO and FECHA", ENTITY_NAME, "idmedicohorariofechaexist");
        }
        if (citaService.findByPacientesAndHorarioAndFecha(citaDTO).isPresent()) {
            throw new BadRequestAlertException("A new cita cannot already have a PACIENTE and HORARIO and FECHA", ENTITY_NAME, "idpacientehorariofechaexist");
        }
        if (citaService.findByPacientesAndMedicos(citaDTO).isPresent()) {
            throw new BadRequestAlertException("A new cita cannot already have a PACIENTE and MEDICO", ENTITY_NAME, "idpacientemedicoexist");
        }
        if (citaDTO.getMedicosId() != null) {
            Optional<MedicoDTO> medicoDTO1 = medicoService.findOne(citaDTO.getMedicosId());
            if(citaDTO.getMedicosId()== medicoDTO1.get().getId() && citaDTO.getEspecialidadId() != medicoDTO1.get().getEspecialidadId()) {
                throw new BadRequestAlertException("A new cita cannot already have a MEDICO and ESPECIALIDAD", ENTITY_NAME, "idmedicoESPEexist");
            }
        }
        if (citaDTO.getMedicosId() != null) {
            Optional<MedicoDTO> medicoDTO2 = medicoService.findOne(citaDTO.getMedicosId());
            if(citaDTO.getMedicosId()== medicoDTO2.get().getId() && citaDTO.getFranjaHorariaId() != medicoDTO2.get().getFranjaHorariaId()) {
                throw new BadRequestAlertException("A new cita cannot already have a MEDICO and FRANJAHORARIA", ENTITY_NAME, "idmedicoFRANJAexist");
            }
        }
        if(citaDTO.getHorarioId()!= null){
            Optional<HorarioDTO> horarioDTO1 = horarioService.findOne(citaDTO.getHorarioId());
            if(citaDTO.getHorarioId() == horarioDTO1.get().getId() && citaDTO.getFranjaHorariaId() != horarioDTO1.get().getFranjaHorariaId()){
                throw new BadRequestAlertException("A new cita cannot already have a HORARIO and FRANJAHORARIA", ENTITY_NAME, "idhorarioFRANJAexist");
            }
        }
        CitaDTO result = citaService.save(citaDTO);
        return ResponseEntity.created(new URI("/api/citas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /citas} : Updates an existing cita.
     *
     * @param citaDTO the citaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated citaDTO,
     * or with status {@code 400 (Bad Request)} if the citaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the citaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/citas")
    public ResponseEntity<CitaDTO> updateCita(@Valid @RequestBody CitaDTO citaDTO) throws URISyntaxException {
        log.debug("REST request to update Cita : {}", citaDTO);
        if (citaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<CitaDTO> citaDTOtemp = citaService
            .findByMedicosAndHorarioAndFecha(citaDTO);
        if (citaDTOtemp.isPresent() && (!citaDTOtemp.get().getId().equals(citaDTO.getId()))) {
            throw new BadRequestAlertException("A new cita cannot already have a MEDICO and HORARIO and FECHA", ENTITY_NAME, "idmedicohorariofechaexist");
        }
        Optional<CitaDTO> citaDTOtemp2 = citaService
            .findByPacientesAndHorarioAndFecha(citaDTO);
        if (citaDTOtemp2.isPresent() && (!citaDTOtemp2.get().getId().equals(citaDTO.getId()))) {
            throw new BadRequestAlertException("A new cita cannot already have a PACIENTE and HORARIO and FECHA", ENTITY_NAME, "idpacientehorariofechaexist");
        }
        Optional<CitaDTO> citaDTOtemp3 = citaService
            .findByPacientesAndMedicos(citaDTO);
        if (citaDTOtemp3.isPresent() && (!citaDTOtemp3.get().getId().equals(citaDTO.getId()))) {
            throw new BadRequestAlertException("A new cita cannot already have a PACIENTE and MEDICO", ENTITY_NAME, "idpacientemedicoexist");
        }
        Long citaDTOTemp4= citaDTO.getMedicosId();
        if (citaDTOTemp4 != null) {
            Optional<MedicoDTO> medicoDTO1 = medicoService.findOne(citaDTO.getMedicosId());
            if(citaDTOTemp4 == medicoDTO1.get().getId() && citaDTO.getEspecialidadId() != medicoDTO1.get().getEspecialidadId()) {
                throw new BadRequestAlertException("A new cita cannot already have a MEDICO and ESPECIALIDAD", ENTITY_NAME, "idmedicoESPEexist");
            }
        }
        Long citaDTOTemp5= citaDTO.getMedicosId();
        if (citaDTOTemp5!= null) {
            Optional<MedicoDTO> medicoDTO2 = medicoService.findOne(citaDTO.getMedicosId());
            if(citaDTOTemp5 == medicoDTO2.get().getId() && citaDTO.getFranjaHorariaId() != medicoDTO2.get().getFranjaHorariaId()) {
                throw new BadRequestAlertException("A new cita cannot already have a MEDICO and FRANJAHORARIA", ENTITY_NAME, "idmedicoFRANJAexist");
            }
        }
        Long citaTemp6= citaDTO.getHorarioId();
        if(citaTemp6!= null){
            Optional<HorarioDTO> horarioDTO1 = horarioService.findOne(citaDTO.getHorarioId());
            if(citaTemp6 == horarioDTO1.get().getId() && citaDTO.getFranjaHorariaId() != horarioDTO1.get().getFranjaHorariaId()){
                throw new BadRequestAlertException("A new cita cannot already have a HORARIO and FRANJAHORARIA", ENTITY_NAME, "idhorarioFRANJAexist");
            }
        }

        CitaDTO result = citaService.save(citaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, citaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /citas} : get all the citas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of citas in body.
     */
    @GetMapping("/citas")
    public ResponseEntity<List<CitaDTO>> getAllCitas(Pageable pageable) {
        log.debug("REST request to get a page of Citas");
        Page<CitaDTO> page = citaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /citas/:id} : get the "id" cita.
     *
     * @param id the id of the citaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the citaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/citas/{id}")
    public ResponseEntity<CitaDTO> getCita(@PathVariable Long id) {
        log.debug("REST request to get Cita : {}", id);
        Optional<CitaDTO> citaDTO = citaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(citaDTO);
    }

    /**
     * {@code DELETE  /citas/:id} : delete the "id" cita.
     *
     * @param id the id of the citaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        log.debug("REST request to delete Cita : {}", id);
        citaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PostMapping("/citas/list")
    public ResponseEntity<List<Cita>> List(@RequestBody BusquedaCitaDTO busquedaCitaDTO){
        CitaCriteria citaCriteria = createCriteriaCita(busquedaCitaDTO);
        List<Cita> list = citaServiceQuery.findByCriterialCita(citaCriteria);
        return new ResponseEntity<List<Cita>>(list, HttpStatus.OK);
    }

    private CitaCriteria createCriteriaCita(BusquedaCitaDTO busquedaCitaDTO){
        CitaCriteria citaCriteria = new CitaCriteria();
        if(busquedaCitaDTO!=null){

            if(!StringUtils.isBlank(busquedaCitaDTO.getMedico())){
                StringFilter filterCitaMedico = new StringFilter();
                filterCitaMedico.setEquals(busquedaCitaDTO.getMedico());
                citaCriteria.setMedico(filterCitaMedico);
            }

            if(!StringUtils.isBlank(busquedaCitaDTO.getPaciente())){
                StringFilter filterCitaPaciente = new StringFilter();
                filterCitaPaciente.setEquals(busquedaCitaDTO.getPaciente());
                citaCriteria.setPaciente(filterCitaPaciente);
            }
        }
        return citaCriteria;
    }
    @GetMapping("/citas/listHora/{franja}")
    public ResponseEntity<List<Horario>> List(@PathVariable Long franja){
        Optional<FranjaHorariaDTO> franjaHorariaDTO = franjaHorariaService.findOne(franja);
        BusquedaHorarioDTO busquedaHorarioDTO = new BusquedaHorarioDTO();
        busquedaHorarioDTO.setFranjaHoraria(franjaHorariaDTO.get().getFranja());
        HorarioCriteria horarioCriteria = createCriteriaHorario(busquedaHorarioDTO);
        List<Horario> list = horarioServiceQuery.findByCriterial(horarioCriteria);
        return new ResponseEntity<List<Horario>>(list, HttpStatus.OK);
    }

    private HorarioCriteria createCriteriaHorario(BusquedaHorarioDTO busquedaHorarioDTO){
        HorarioCriteria horarioCriteria = new HorarioCriteria();
        if(busquedaHorarioDTO!=null){
            if(!StringUtils.isBlank(busquedaHorarioDTO.getFranjaHoraria())){
                StringFilter filterCitaFranjaHor = new StringFilter();
                filterCitaFranjaHor.setEquals(busquedaHorarioDTO.getFranjaHoraria());
                horarioCriteria.setFranjaHoraria(filterCitaFranjaHor);
            }
        }
        return horarioCriteria;
    }
}

package quileia.com.web.rest;

import quileia.com.QuileiaJavaWebApp;
import quileia.com.domain.Cita;
import quileia.com.domain.Especialidad;
import quileia.com.domain.FranjaHoraria;
import quileia.com.domain.Horario;
import quileia.com.domain.Medico;
import quileia.com.domain.Paciente;
import quileia.com.repository.CitaRepository;
import quileia.com.service.CitaService;
import quileia.com.service.dto.CitaDTO;
import quileia.com.service.mapper.CitaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CitaResource} REST controller.
 */
@SpringBootTest(classes = QuileiaJavaWebApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CitaResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private CitaService citaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCitaMockMvc;

    private Cita cita;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cita createEntity(EntityManager em) {
        Cita cita = new Cita()
            .fecha(DEFAULT_FECHA);
        // Add required entity
        Especialidad especialidad;
        if (TestUtil.findAll(em, Especialidad.class).isEmpty()) {
            especialidad = EspecialidadResourceIT.createEntity(em);
            em.persist(especialidad);
            em.flush();
        } else {
            especialidad = TestUtil.findAll(em, Especialidad.class).get(0);
        }
        cita.setEspecialidad(especialidad);
        // Add required entity
        FranjaHoraria franjaHoraria;
        if (TestUtil.findAll(em, FranjaHoraria.class).isEmpty()) {
            franjaHoraria = FranjaHorariaResourceIT.createEntity(em);
            em.persist(franjaHoraria);
            em.flush();
        } else {
            franjaHoraria = TestUtil.findAll(em, FranjaHoraria.class).get(0);
        }
        cita.setFranjaHoraria(franjaHoraria);
        // Add required entity
        Horario horario;
        if (TestUtil.findAll(em, Horario.class).isEmpty()) {
            horario = HorarioResourceIT.createEntity(em);
            em.persist(horario);
            em.flush();
        } else {
            horario = TestUtil.findAll(em, Horario.class).get(0);
        }
        cita.setHorario(horario);
        // Add required entity
        Medico medico;
        if (TestUtil.findAll(em, Medico.class).isEmpty()) {
            medico = MedicoResourceIT.createEntity(em);
            em.persist(medico);
            em.flush();
        } else {
            medico = TestUtil.findAll(em, Medico.class).get(0);
        }
        cita.setMedicos(medico);
        // Add required entity
        Paciente paciente;
        if (TestUtil.findAll(em, Paciente.class).isEmpty()) {
            paciente = PacienteResourceIT.createEntity(em);
            em.persist(paciente);
            em.flush();
        } else {
            paciente = TestUtil.findAll(em, Paciente.class).get(0);
        }
        cita.setPacientes(paciente);
        return cita;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cita createUpdatedEntity(EntityManager em) {
        Cita cita = new Cita()
            .fecha(UPDATED_FECHA);
        // Add required entity
        Especialidad especialidad;
        if (TestUtil.findAll(em, Especialidad.class).isEmpty()) {
            especialidad = EspecialidadResourceIT.createUpdatedEntity(em);
            em.persist(especialidad);
            em.flush();
        } else {
            especialidad = TestUtil.findAll(em, Especialidad.class).get(0);
        }
        cita.setEspecialidad(especialidad);
        // Add required entity
        FranjaHoraria franjaHoraria;
        if (TestUtil.findAll(em, FranjaHoraria.class).isEmpty()) {
            franjaHoraria = FranjaHorariaResourceIT.createUpdatedEntity(em);
            em.persist(franjaHoraria);
            em.flush();
        } else {
            franjaHoraria = TestUtil.findAll(em, FranjaHoraria.class).get(0);
        }
        cita.setFranjaHoraria(franjaHoraria);
        // Add required entity
        Horario horario;
        if (TestUtil.findAll(em, Horario.class).isEmpty()) {
            horario = HorarioResourceIT.createUpdatedEntity(em);
            em.persist(horario);
            em.flush();
        } else {
            horario = TestUtil.findAll(em, Horario.class).get(0);
        }
        cita.setHorario(horario);
        // Add required entity
        Medico medico;
        if (TestUtil.findAll(em, Medico.class).isEmpty()) {
            medico = MedicoResourceIT.createUpdatedEntity(em);
            em.persist(medico);
            em.flush();
        } else {
            medico = TestUtil.findAll(em, Medico.class).get(0);
        }
        cita.setMedicos(medico);
        // Add required entity
        Paciente paciente;
        if (TestUtil.findAll(em, Paciente.class).isEmpty()) {
            paciente = PacienteResourceIT.createUpdatedEntity(em);
            em.persist(paciente);
            em.flush();
        } else {
            paciente = TestUtil.findAll(em, Paciente.class).get(0);
        }
        cita.setPacientes(paciente);
        return cita;
    }

    @BeforeEach
    public void initTest() {
        cita = createEntity(em);
    }

    @Test
    @Transactional
    public void createCita() throws Exception {
        int databaseSizeBeforeCreate = citaRepository.findAll().size();

        // Create the Cita
        CitaDTO citaDTO = citaMapper.toDto(cita);
        restCitaMockMvc.perform(post("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citaDTO)))
            .andExpect(status().isCreated());

        // Validate the Cita in the database
        List<Cita> citaList = citaRepository.findAll();
        assertThat(citaList).hasSize(databaseSizeBeforeCreate + 1);
        Cita testCita = citaList.get(citaList.size() - 1);
        assertThat(testCita.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    public void createCitaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = citaRepository.findAll().size();

        // Create the Cita with an existing ID
        cita.setId(1L);
        CitaDTO citaDTO = citaMapper.toDto(cita);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCitaMockMvc.perform(post("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cita in the database
        List<Cita> citaList = citaRepository.findAll();
        assertThat(citaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = citaRepository.findAll().size();
        // set the field null
        cita.setFecha(null);

        // Create the Cita, which fails.
        CitaDTO citaDTO = citaMapper.toDto(cita);

        restCitaMockMvc.perform(post("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citaDTO)))
            .andExpect(status().isBadRequest());

        List<Cita> citaList = citaRepository.findAll();
        assertThat(citaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCitas() throws Exception {
        // Initialize the database
        citaRepository.saveAndFlush(cita);

        // Get all the citaList
        restCitaMockMvc.perform(get("/api/citas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cita.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }
    
    @Test
    @Transactional
    public void getCita() throws Exception {
        // Initialize the database
        citaRepository.saveAndFlush(cita);

        // Get the cita
        restCitaMockMvc.perform(get("/api/citas/{id}", cita.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cita.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCita() throws Exception {
        // Get the cita
        restCitaMockMvc.perform(get("/api/citas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCita() throws Exception {
        // Initialize the database
        citaRepository.saveAndFlush(cita);

        int databaseSizeBeforeUpdate = citaRepository.findAll().size();

        // Update the cita
        Cita updatedCita = citaRepository.findById(cita.getId()).get();
        // Disconnect from session so that the updates on updatedCita are not directly saved in db
        em.detach(updatedCita);
        updatedCita
            .fecha(UPDATED_FECHA);
        CitaDTO citaDTO = citaMapper.toDto(updatedCita);

        restCitaMockMvc.perform(put("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citaDTO)))
            .andExpect(status().isOk());

        // Validate the Cita in the database
        List<Cita> citaList = citaRepository.findAll();
        assertThat(citaList).hasSize(databaseSizeBeforeUpdate);
        Cita testCita = citaList.get(citaList.size() - 1);
        assertThat(testCita.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void updateNonExistingCita() throws Exception {
        int databaseSizeBeforeUpdate = citaRepository.findAll().size();

        // Create the Cita
        CitaDTO citaDTO = citaMapper.toDto(cita);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCitaMockMvc.perform(put("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cita in the database
        List<Cita> citaList = citaRepository.findAll();
        assertThat(citaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCita() throws Exception {
        // Initialize the database
        citaRepository.saveAndFlush(cita);

        int databaseSizeBeforeDelete = citaRepository.findAll().size();

        // Delete the cita
        restCitaMockMvc.perform(delete("/api/citas/{id}", cita.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cita> citaList = citaRepository.findAll();
        assertThat(citaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package quileia.com.web.rest;

import quileia.com.QuileiaJavaWebApp;
import quileia.com.domain.Medico;
import quileia.com.domain.TipoDocumento;
import quileia.com.domain.Especialidad;
import quileia.com.domain.FranjaHoraria;
import quileia.com.repository.MedicoRepository;
import quileia.com.service.MedicoService;
import quileia.com.service.dto.MedicoDTO;
import quileia.com.service.mapper.MedicoMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedicoResource} REST controller.
 */
@SpringBootTest(classes = QuileiaJavaWebApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MedicoResourceIT {

    private static final String DEFAULT_NOMBRE_COMPLETO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_COMPLETO = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICACION = "BBBBBBBBBB";

    private static final String DEFAULT_TARJETA_PROFESIONAL = "AAAAAAAAAA";
    private static final String UPDATED_TARJETA_PROFESIONAL = "BBBBBBBBBB";

    private static final Double DEFAULT_ANOS_DE_EXPERIENCIA = 1D;
    private static final Double UPDATED_ANOS_DE_EXPERIENCIA = 2D;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicoMockMvc;

    private Medico medico;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medico createEntity(EntityManager em) {
        Medico medico = new Medico()
            .nombreCompleto(DEFAULT_NOMBRE_COMPLETO)
            .identificacion(DEFAULT_IDENTIFICACION)
            .tarjetaProfesional(DEFAULT_TARJETA_PROFESIONAL)
            .anosDeExperiencia(DEFAULT_ANOS_DE_EXPERIENCIA);
        // Add required entity
        TipoDocumento tipoDocumento;
        if (TestUtil.findAll(em, TipoDocumento.class).isEmpty()) {
            tipoDocumento = TipoDocumentoResourceIT.createEntity(em);
            em.persist(tipoDocumento);
            em.flush();
        } else {
            tipoDocumento = TestUtil.findAll(em, TipoDocumento.class).get(0);
        }
        medico.setTipoDocumento(tipoDocumento);
        // Add required entity
        Especialidad especialidad;
        if (TestUtil.findAll(em, Especialidad.class).isEmpty()) {
            especialidad = EspecialidadResourceIT.createEntity(em);
            em.persist(especialidad);
            em.flush();
        } else {
            especialidad = TestUtil.findAll(em, Especialidad.class).get(0);
        }
        medico.setEspecialidad(especialidad);
        // Add required entity
        FranjaHoraria franjaHoraria;
        if (TestUtil.findAll(em, FranjaHoraria.class).isEmpty()) {
            franjaHoraria = FranjaHorariaResourceIT.createEntity(em);
            em.persist(franjaHoraria);
            em.flush();
        } else {
            franjaHoraria = TestUtil.findAll(em, FranjaHoraria.class).get(0);
        }
        medico.setFranjaHoraria(franjaHoraria);
        return medico;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medico createUpdatedEntity(EntityManager em) {
        Medico medico = new Medico()
            .nombreCompleto(UPDATED_NOMBRE_COMPLETO)
            .identificacion(UPDATED_IDENTIFICACION)
            .tarjetaProfesional(UPDATED_TARJETA_PROFESIONAL)
            .anosDeExperiencia(UPDATED_ANOS_DE_EXPERIENCIA);
        // Add required entity
        TipoDocumento tipoDocumento;
        if (TestUtil.findAll(em, TipoDocumento.class).isEmpty()) {
            tipoDocumento = TipoDocumentoResourceIT.createUpdatedEntity(em);
            em.persist(tipoDocumento);
            em.flush();
        } else {
            tipoDocumento = TestUtil.findAll(em, TipoDocumento.class).get(0);
        }
        medico.setTipoDocumento(tipoDocumento);
        // Add required entity
        Especialidad especialidad;
        if (TestUtil.findAll(em, Especialidad.class).isEmpty()) {
            especialidad = EspecialidadResourceIT.createUpdatedEntity(em);
            em.persist(especialidad);
            em.flush();
        } else {
            especialidad = TestUtil.findAll(em, Especialidad.class).get(0);
        }
        medico.setEspecialidad(especialidad);
        // Add required entity
        FranjaHoraria franjaHoraria;
        if (TestUtil.findAll(em, FranjaHoraria.class).isEmpty()) {
            franjaHoraria = FranjaHorariaResourceIT.createUpdatedEntity(em);
            em.persist(franjaHoraria);
            em.flush();
        } else {
            franjaHoraria = TestUtil.findAll(em, FranjaHoraria.class).get(0);
        }
        medico.setFranjaHoraria(franjaHoraria);
        return medico;
    }

    @BeforeEach
    public void initTest() {
        medico = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedico() throws Exception {
        int databaseSizeBeforeCreate = medicoRepository.findAll().size();

        // Create the Medico
        MedicoDTO medicoDTO = medicoMapper.toDto(medico);
        restMedicoMockMvc.perform(post("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isCreated());

        // Validate the Medico in the database
        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeCreate + 1);
        Medico testMedico = medicoList.get(medicoList.size() - 1);
        assertThat(testMedico.getNombreCompleto()).isEqualTo(DEFAULT_NOMBRE_COMPLETO);
        assertThat(testMedico.getIdentificacion()).isEqualTo(DEFAULT_IDENTIFICACION);
        assertThat(testMedico.getTarjetaProfesional()).isEqualTo(DEFAULT_TARJETA_PROFESIONAL);
        assertThat(testMedico.getAnosDeExperiencia()).isEqualTo(DEFAULT_ANOS_DE_EXPERIENCIA);
    }

    @Test
    @Transactional
    public void createMedicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicoRepository.findAll().size();

        // Create the Medico with an existing ID
        medico.setId(1L);
        MedicoDTO medicoDTO = medicoMapper.toDto(medico);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicoMockMvc.perform(post("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Medico in the database
        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreCompletoIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicoRepository.findAll().size();
        // set the field null
        medico.setNombreCompleto(null);

        // Create the Medico, which fails.
        MedicoDTO medicoDTO = medicoMapper.toDto(medico);

        restMedicoMockMvc.perform(post("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isBadRequest());

        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdentificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicoRepository.findAll().size();
        // set the field null
        medico.setIdentificacion(null);

        // Create the Medico, which fails.
        MedicoDTO medicoDTO = medicoMapper.toDto(medico);

        restMedicoMockMvc.perform(post("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isBadRequest());

        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarjetaProfesionalIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicoRepository.findAll().size();
        // set the field null
        medico.setTarjetaProfesional(null);

        // Create the Medico, which fails.
        MedicoDTO medicoDTO = medicoMapper.toDto(medico);

        restMedicoMockMvc.perform(post("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isBadRequest());

        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicos() throws Exception {
        // Initialize the database
        medicoRepository.saveAndFlush(medico);

        // Get all the medicoList
        restMedicoMockMvc.perform(get("/api/medicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medico.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreCompleto").value(hasItem(DEFAULT_NOMBRE_COMPLETO)))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].tarjetaProfesional").value(hasItem(DEFAULT_TARJETA_PROFESIONAL)))
            .andExpect(jsonPath("$.[*].anosDeExperiencia").value(hasItem(DEFAULT_ANOS_DE_EXPERIENCIA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMedico() throws Exception {
        // Initialize the database
        medicoRepository.saveAndFlush(medico);

        // Get the medico
        restMedicoMockMvc.perform(get("/api/medicos/{id}", medico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medico.getId().intValue()))
            .andExpect(jsonPath("$.nombreCompleto").value(DEFAULT_NOMBRE_COMPLETO))
            .andExpect(jsonPath("$.identificacion").value(DEFAULT_IDENTIFICACION))
            .andExpect(jsonPath("$.tarjetaProfesional").value(DEFAULT_TARJETA_PROFESIONAL))
            .andExpect(jsonPath("$.anosDeExperiencia").value(DEFAULT_ANOS_DE_EXPERIENCIA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMedico() throws Exception {
        // Get the medico
        restMedicoMockMvc.perform(get("/api/medicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedico() throws Exception {
        // Initialize the database
        medicoRepository.saveAndFlush(medico);

        int databaseSizeBeforeUpdate = medicoRepository.findAll().size();

        // Update the medico
        Medico updatedMedico = medicoRepository.findById(medico.getId()).get();
        // Disconnect from session so that the updates on updatedMedico are not directly saved in db
        em.detach(updatedMedico);
        updatedMedico
            .nombreCompleto(UPDATED_NOMBRE_COMPLETO)
            .identificacion(UPDATED_IDENTIFICACION)
            .tarjetaProfesional(UPDATED_TARJETA_PROFESIONAL)
            .anosDeExperiencia(UPDATED_ANOS_DE_EXPERIENCIA);
        MedicoDTO medicoDTO = medicoMapper.toDto(updatedMedico);

        restMedicoMockMvc.perform(put("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isOk());

        // Validate the Medico in the database
        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeUpdate);
        Medico testMedico = medicoList.get(medicoList.size() - 1);
        assertThat(testMedico.getNombreCompleto()).isEqualTo(UPDATED_NOMBRE_COMPLETO);
        assertThat(testMedico.getIdentificacion()).isEqualTo(UPDATED_IDENTIFICACION);
        assertThat(testMedico.getTarjetaProfesional()).isEqualTo(UPDATED_TARJETA_PROFESIONAL);
        assertThat(testMedico.getAnosDeExperiencia()).isEqualTo(UPDATED_ANOS_DE_EXPERIENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingMedico() throws Exception {
        int databaseSizeBeforeUpdate = medicoRepository.findAll().size();

        // Create the Medico
        MedicoDTO medicoDTO = medicoMapper.toDto(medico);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicoMockMvc.perform(put("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Medico in the database
        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedico() throws Exception {
        // Initialize the database
        medicoRepository.saveAndFlush(medico);

        int databaseSizeBeforeDelete = medicoRepository.findAll().size();

        // Delete the medico
        restMedicoMockMvc.perform(delete("/api/medicos/{id}", medico.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Medico> medicoList = medicoRepository.findAll();
        assertThat(medicoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

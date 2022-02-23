package quileia.com.web.rest;

import quileia.com.QuileiaJavaWebApp;
import quileia.com.domain.Especialidad;
import quileia.com.repository.EspecialidadRepository;
import quileia.com.service.EspecialidadService;
import quileia.com.service.dto.EspecialidadDTO;
import quileia.com.service.mapper.EspecialidadMapper;

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

import quileia.com.domain.enumeration.Estado;
/**
 * Integration tests for the {@link EspecialidadResource} REST controller.
 */
@SpringBootTest(classes = QuileiaJavaWebApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EspecialidadResourceIT {

    private static final String DEFAULT_NOMBRE_ESPECIALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ESPECIALIDAD = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO_ESPECIALIDAD = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO_ESPECIALIDAD = Estado.INACTIVO;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private EspecialidadMapper especialidadMapper;

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEspecialidadMockMvc;

    private Especialidad especialidad;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Especialidad createEntity(EntityManager em) {
        Especialidad especialidad = new Especialidad()
            .nombreEspecialidad(DEFAULT_NOMBRE_ESPECIALIDAD)
            .estadoEspecialidad(DEFAULT_ESTADO_ESPECIALIDAD);
        return especialidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Especialidad createUpdatedEntity(EntityManager em) {
        Especialidad especialidad = new Especialidad()
            .nombreEspecialidad(UPDATED_NOMBRE_ESPECIALIDAD)
            .estadoEspecialidad(UPDATED_ESTADO_ESPECIALIDAD);
        return especialidad;
    }

    @BeforeEach
    public void initTest() {
        especialidad = createEntity(em);
    }

    @Test
    @Transactional
    public void createEspecialidad() throws Exception {
        int databaseSizeBeforeCreate = especialidadRepository.findAll().size();

        // Create the Especialidad
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(especialidad);
        restEspecialidadMockMvc.perform(post("/api/especialidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadDTO)))
            .andExpect(status().isCreated());

        // Validate the Especialidad in the database
        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeCreate + 1);
        Especialidad testEspecialidad = especialidadList.get(especialidadList.size() - 1);
        assertThat(testEspecialidad.getNombreEspecialidad()).isEqualTo(DEFAULT_NOMBRE_ESPECIALIDAD);
        assertThat(testEspecialidad.getEstadoEspecialidad()).isEqualTo(DEFAULT_ESTADO_ESPECIALIDAD);
    }

    @Test
    @Transactional
    public void createEspecialidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = especialidadRepository.findAll().size();

        // Create the Especialidad with an existing ID
        especialidad.setId(1L);
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(especialidad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEspecialidadMockMvc.perform(post("/api/especialidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Especialidad in the database
        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreEspecialidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = especialidadRepository.findAll().size();
        // set the field null
        especialidad.setNombreEspecialidad(null);

        // Create the Especialidad, which fails.
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(especialidad);

        restEspecialidadMockMvc.perform(post("/api/especialidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadDTO)))
            .andExpect(status().isBadRequest());

        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoEspecialidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = especialidadRepository.findAll().size();
        // set the field null
        especialidad.setEstadoEspecialidad(null);

        // Create the Especialidad, which fails.
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(especialidad);

        restEspecialidadMockMvc.perform(post("/api/especialidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadDTO)))
            .andExpect(status().isBadRequest());

        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEspecialidads() throws Exception {
        // Initialize the database
        especialidadRepository.saveAndFlush(especialidad);

        // Get all the especialidadList
        restEspecialidadMockMvc.perform(get("/api/especialidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(especialidad.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreEspecialidad").value(hasItem(DEFAULT_NOMBRE_ESPECIALIDAD)))
            .andExpect(jsonPath("$.[*].estadoEspecialidad").value(hasItem(DEFAULT_ESTADO_ESPECIALIDAD.toString())));
    }
    
    @Test
    @Transactional
    public void getEspecialidad() throws Exception {
        // Initialize the database
        especialidadRepository.saveAndFlush(especialidad);

        // Get the especialidad
        restEspecialidadMockMvc.perform(get("/api/especialidads/{id}", especialidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(especialidad.getId().intValue()))
            .andExpect(jsonPath("$.nombreEspecialidad").value(DEFAULT_NOMBRE_ESPECIALIDAD))
            .andExpect(jsonPath("$.estadoEspecialidad").value(DEFAULT_ESTADO_ESPECIALIDAD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEspecialidad() throws Exception {
        // Get the especialidad
        restEspecialidadMockMvc.perform(get("/api/especialidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEspecialidad() throws Exception {
        // Initialize the database
        especialidadRepository.saveAndFlush(especialidad);

        int databaseSizeBeforeUpdate = especialidadRepository.findAll().size();

        // Update the especialidad
        Especialidad updatedEspecialidad = especialidadRepository.findById(especialidad.getId()).get();
        // Disconnect from session so that the updates on updatedEspecialidad are not directly saved in db
        em.detach(updatedEspecialidad);
        updatedEspecialidad
            .nombreEspecialidad(UPDATED_NOMBRE_ESPECIALIDAD)
            .estadoEspecialidad(UPDATED_ESTADO_ESPECIALIDAD);
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(updatedEspecialidad);

        restEspecialidadMockMvc.perform(put("/api/especialidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadDTO)))
            .andExpect(status().isOk());

        // Validate the Especialidad in the database
        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeUpdate);
        Especialidad testEspecialidad = especialidadList.get(especialidadList.size() - 1);
        assertThat(testEspecialidad.getNombreEspecialidad()).isEqualTo(UPDATED_NOMBRE_ESPECIALIDAD);
        assertThat(testEspecialidad.getEstadoEspecialidad()).isEqualTo(UPDATED_ESTADO_ESPECIALIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingEspecialidad() throws Exception {
        int databaseSizeBeforeUpdate = especialidadRepository.findAll().size();

        // Create the Especialidad
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(especialidad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEspecialidadMockMvc.perform(put("/api/especialidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(especialidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Especialidad in the database
        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEspecialidad() throws Exception {
        // Initialize the database
        especialidadRepository.saveAndFlush(especialidad);

        int databaseSizeBeforeDelete = especialidadRepository.findAll().size();

        // Delete the especialidad
        restEspecialidadMockMvc.perform(delete("/api/especialidads/{id}", especialidad.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Especialidad> especialidadList = especialidadRepository.findAll();
        assertThat(especialidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

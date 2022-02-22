package quileia.com.web.rest;

import quileia.com.QuileiaJavaWebApp;
import quileia.com.domain.FranjaHoraria;
import quileia.com.repository.FranjaHorariaRepository;
import quileia.com.service.FranjaHorariaService;
import quileia.com.service.dto.FranjaHorariaDTO;
import quileia.com.service.mapper.FranjaHorariaMapper;

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
 * Integration tests for the {@link FranjaHorariaResource} REST controller.
 */
@SpringBootTest(classes = QuileiaJavaWebApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FranjaHorariaResourceIT {

    private static final String DEFAULT_FRANJA = "AAAAAAAAAA";
    private static final String UPDATED_FRANJA = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO_FRANJA_HORARIA = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO_FRANJA_HORARIA = Estado.INACTIVO;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    @Autowired
    private FranjaHorariaMapper franjaHorariaMapper;

    @Autowired
    private FranjaHorariaService franjaHorariaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFranjaHorariaMockMvc;

    private FranjaHoraria franjaHoraria;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FranjaHoraria createEntity(EntityManager em) {
        FranjaHoraria franjaHoraria = new FranjaHoraria()
            .franja(DEFAULT_FRANJA)
            .estadoFranjaHoraria(DEFAULT_ESTADO_FRANJA_HORARIA);
        return franjaHoraria;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FranjaHoraria createUpdatedEntity(EntityManager em) {
        FranjaHoraria franjaHoraria = new FranjaHoraria()
            .franja(UPDATED_FRANJA)
            .estadoFranjaHoraria(UPDATED_ESTADO_FRANJA_HORARIA);
        return franjaHoraria;
    }

    @BeforeEach
    public void initTest() {
        franjaHoraria = createEntity(em);
    }

    @Test
    @Transactional
    public void createFranjaHoraria() throws Exception {
        int databaseSizeBeforeCreate = franjaHorariaRepository.findAll().size();

        // Create the FranjaHoraria
        FranjaHorariaDTO franjaHorariaDTO = franjaHorariaMapper.toDto(franjaHoraria);
        restFranjaHorariaMockMvc.perform(post("/api/franja-horarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franjaHorariaDTO)))
            .andExpect(status().isCreated());

        // Validate the FranjaHoraria in the database
        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeCreate + 1);
        FranjaHoraria testFranjaHoraria = franjaHorariaList.get(franjaHorariaList.size() - 1);
        assertThat(testFranjaHoraria.getFranja()).isEqualTo(DEFAULT_FRANJA);
        assertThat(testFranjaHoraria.getEstadoFranjaHoraria()).isEqualTo(DEFAULT_ESTADO_FRANJA_HORARIA);
    }

    @Test
    @Transactional
    public void createFranjaHorariaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = franjaHorariaRepository.findAll().size();

        // Create the FranjaHoraria with an existing ID
        franjaHoraria.setId(1L);
        FranjaHorariaDTO franjaHorariaDTO = franjaHorariaMapper.toDto(franjaHoraria);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFranjaHorariaMockMvc.perform(post("/api/franja-horarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franjaHorariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FranjaHoraria in the database
        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFranjaIsRequired() throws Exception {
        int databaseSizeBeforeTest = franjaHorariaRepository.findAll().size();
        // set the field null
        franjaHoraria.setFranja(null);

        // Create the FranjaHoraria, which fails.
        FranjaHorariaDTO franjaHorariaDTO = franjaHorariaMapper.toDto(franjaHoraria);

        restFranjaHorariaMockMvc.perform(post("/api/franja-horarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franjaHorariaDTO)))
            .andExpect(status().isBadRequest());

        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoFranjaHorariaIsRequired() throws Exception {
        int databaseSizeBeforeTest = franjaHorariaRepository.findAll().size();
        // set the field null
        franjaHoraria.setEstadoFranjaHoraria(null);

        // Create the FranjaHoraria, which fails.
        FranjaHorariaDTO franjaHorariaDTO = franjaHorariaMapper.toDto(franjaHoraria);

        restFranjaHorariaMockMvc.perform(post("/api/franja-horarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franjaHorariaDTO)))
            .andExpect(status().isBadRequest());

        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFranjaHorarias() throws Exception {
        // Initialize the database
        franjaHorariaRepository.saveAndFlush(franjaHoraria);

        // Get all the franjaHorariaList
        restFranjaHorariaMockMvc.perform(get("/api/franja-horarias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(franjaHoraria.getId().intValue())))
            .andExpect(jsonPath("$.[*].franja").value(hasItem(DEFAULT_FRANJA)))
            .andExpect(jsonPath("$.[*].estadoFranjaHoraria").value(hasItem(DEFAULT_ESTADO_FRANJA_HORARIA.toString())));
    }
    
    @Test
    @Transactional
    public void getFranjaHoraria() throws Exception {
        // Initialize the database
        franjaHorariaRepository.saveAndFlush(franjaHoraria);

        // Get the franjaHoraria
        restFranjaHorariaMockMvc.perform(get("/api/franja-horarias/{id}", franjaHoraria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(franjaHoraria.getId().intValue()))
            .andExpect(jsonPath("$.franja").value(DEFAULT_FRANJA))
            .andExpect(jsonPath("$.estadoFranjaHoraria").value(DEFAULT_ESTADO_FRANJA_HORARIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFranjaHoraria() throws Exception {
        // Get the franjaHoraria
        restFranjaHorariaMockMvc.perform(get("/api/franja-horarias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFranjaHoraria() throws Exception {
        // Initialize the database
        franjaHorariaRepository.saveAndFlush(franjaHoraria);

        int databaseSizeBeforeUpdate = franjaHorariaRepository.findAll().size();

        // Update the franjaHoraria
        FranjaHoraria updatedFranjaHoraria = franjaHorariaRepository.findById(franjaHoraria.getId()).get();
        // Disconnect from session so that the updates on updatedFranjaHoraria are not directly saved in db
        em.detach(updatedFranjaHoraria);
        updatedFranjaHoraria
            .franja(UPDATED_FRANJA)
            .estadoFranjaHoraria(UPDATED_ESTADO_FRANJA_HORARIA);
        FranjaHorariaDTO franjaHorariaDTO = franjaHorariaMapper.toDto(updatedFranjaHoraria);

        restFranjaHorariaMockMvc.perform(put("/api/franja-horarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franjaHorariaDTO)))
            .andExpect(status().isOk());

        // Validate the FranjaHoraria in the database
        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeUpdate);
        FranjaHoraria testFranjaHoraria = franjaHorariaList.get(franjaHorariaList.size() - 1);
        assertThat(testFranjaHoraria.getFranja()).isEqualTo(UPDATED_FRANJA);
        assertThat(testFranjaHoraria.getEstadoFranjaHoraria()).isEqualTo(UPDATED_ESTADO_FRANJA_HORARIA);
    }

    @Test
    @Transactional
    public void updateNonExistingFranjaHoraria() throws Exception {
        int databaseSizeBeforeUpdate = franjaHorariaRepository.findAll().size();

        // Create the FranjaHoraria
        FranjaHorariaDTO franjaHorariaDTO = franjaHorariaMapper.toDto(franjaHoraria);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFranjaHorariaMockMvc.perform(put("/api/franja-horarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franjaHorariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FranjaHoraria in the database
        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFranjaHoraria() throws Exception {
        // Initialize the database
        franjaHorariaRepository.saveAndFlush(franjaHoraria);

        int databaseSizeBeforeDelete = franjaHorariaRepository.findAll().size();

        // Delete the franjaHoraria
        restFranjaHorariaMockMvc.perform(delete("/api/franja-horarias/{id}", franjaHoraria.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FranjaHoraria> franjaHorariaList = franjaHorariaRepository.findAll();
        assertThat(franjaHorariaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package midianet.road.web.rest;

import midianet.road.RoadApp;

import midianet.road.domain.QuartoTipo;
import midianet.road.repository.QuartoTipoRepository;
import midianet.road.service.QuartoTipoService;
import midianet.road.service.dto.QuartoTipoDTO;
import midianet.road.service.mapper.QuartoTipoMapper;
import midianet.road.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuartoTipoResource REST controller.
 *
 * @see QuartoTipoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoadApp.class)
public class QuartoTipoResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR = new BigDecimal(2);

    @Autowired
    private QuartoTipoRepository quartoTipoRepository;

    @Autowired
    private QuartoTipoMapper quartoTipoMapper;

    @Autowired
    private QuartoTipoService quartoTipoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuartoTipoMockMvc;

    private QuartoTipo quartoTipo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuartoTipoResource quartoTipoResource = new QuartoTipoResource(quartoTipoService);
        this.restQuartoTipoMockMvc = MockMvcBuilders.standaloneSetup(quartoTipoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuartoTipo createEntity(EntityManager em) {
        QuartoTipo quartoTipo = new QuartoTipo()
            .descricao(DEFAULT_DESCRICAO)
            .valor(DEFAULT_VALOR);
        return quartoTipo;
    }

    @Before
    public void initTest() {
        quartoTipo = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuartoTipo() throws Exception {
        int databaseSizeBeforeCreate = quartoTipoRepository.findAll().size();

        // Create the QuartoTipo
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);
        restQuartoTipoMockMvc.perform(post("/api/quarto-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoTipoDTO)))
            .andExpect(status().isCreated());

        // Validate the QuartoTipo in the database
        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeCreate + 1);
        QuartoTipo testQuartoTipo = quartoTipoList.get(quartoTipoList.size() - 1);
        assertThat(testQuartoTipo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testQuartoTipo.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createQuartoTipoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quartoTipoRepository.findAll().size();

        // Create the QuartoTipo with an existing ID
        quartoTipo.setId(1L);
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuartoTipoMockMvc.perform(post("/api/quarto-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoTipoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = quartoTipoRepository.findAll().size();
        // set the field null
        quartoTipo.setDescricao(null);

        // Create the QuartoTipo, which fails.
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);

        restQuartoTipoMockMvc.perform(post("/api/quarto-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoTipoDTO)))
            .andExpect(status().isBadRequest());

        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = quartoTipoRepository.findAll().size();
        // set the field null
        quartoTipo.setValor(null);

        // Create the QuartoTipo, which fails.
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);

        restQuartoTipoMockMvc.perform(post("/api/quarto-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoTipoDTO)))
            .andExpect(status().isBadRequest());

        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuartoTipos() throws Exception {
        // Initialize the database
        quartoTipoRepository.saveAndFlush(quartoTipo);

        // Get all the quartoTipoList
        restQuartoTipoMockMvc.perform(get("/api/quarto-tipos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quartoTipo.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.intValue())));
    }

    @Test
    @Transactional
    public void getQuartoTipo() throws Exception {
        // Initialize the database
        quartoTipoRepository.saveAndFlush(quartoTipo);

        // Get the quartoTipo
        restQuartoTipoMockMvc.perform(get("/api/quarto-tipos/{id}", quartoTipo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quartoTipo.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuartoTipo() throws Exception {
        // Get the quartoTipo
        restQuartoTipoMockMvc.perform(get("/api/quarto-tipos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuartoTipo() throws Exception {
        // Initialize the database
        quartoTipoRepository.saveAndFlush(quartoTipo);
        int databaseSizeBeforeUpdate = quartoTipoRepository.findAll().size();

        // Update the quartoTipo
        QuartoTipo updatedQuartoTipo = quartoTipoRepository.findOne(quartoTipo.getId());
        updatedQuartoTipo
            .descricao(UPDATED_DESCRICAO)
            .valor(UPDATED_VALOR);
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(updatedQuartoTipo);

        restQuartoTipoMockMvc.perform(put("/api/quarto-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoTipoDTO)))
            .andExpect(status().isOk());

        // Validate the QuartoTipo in the database
        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeUpdate);
        QuartoTipo testQuartoTipo = quartoTipoList.get(quartoTipoList.size() - 1);
        assertThat(testQuartoTipo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testQuartoTipo.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingQuartoTipo() throws Exception {
        int databaseSizeBeforeUpdate = quartoTipoRepository.findAll().size();

        // Create the QuartoTipo
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuartoTipoMockMvc.perform(put("/api/quarto-tipos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoTipoDTO)))
            .andExpect(status().isCreated());

        // Validate the QuartoTipo in the database
        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuartoTipo() throws Exception {
        // Initialize the database
        quartoTipoRepository.saveAndFlush(quartoTipo);
        int databaseSizeBeforeDelete = quartoTipoRepository.findAll().size();

        // Get the quartoTipo
        restQuartoTipoMockMvc.perform(delete("/api/quarto-tipos/{id}", quartoTipo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuartoTipo> quartoTipoList = quartoTipoRepository.findAll();
        assertThat(quartoTipoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuartoTipo.class);
    }
}

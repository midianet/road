package midianet.road.web.rest;

import midianet.road.RoadApp;

import midianet.road.domain.Quarto;
import midianet.road.repository.QuartoRepository;
import midianet.road.service.QuartoService;
import midianet.road.service.dto.QuartoDTO;
import midianet.road.service.mapper.QuartoMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import midianet.road.domain.enumeration.QuartoSexo;
/**
 * Test class for the QuartoResource REST controller.
 *
 * @see QuartoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoadApp.class)
public class QuartoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final QuartoSexo DEFAULT_CLASSE = QuartoSexo.FEMININO;
    private static final QuartoSexo UPDATED_CLASSE = QuartoSexo.MASCULINO;

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private QuartoMapper quartoMapper;

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuartoMockMvc;

    private Quarto quarto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuartoResource quartoResource = new QuartoResource(quartoService);
        this.restQuartoMockMvc = MockMvcBuilders.standaloneSetup(quartoResource)
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
    public static Quarto createEntity(EntityManager em) {
        Quarto quarto = new Quarto()
            .nome(DEFAULT_NOME)
            .classe(DEFAULT_CLASSE);
        return quarto;
    }

    @Before
    public void initTest() {
        quarto = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuarto() throws Exception {
        int databaseSizeBeforeCreate = quartoRepository.findAll().size();

        // Create the Quarto
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(quarto);
        restQuartoMockMvc.perform(post("/api/quartos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoDTO)))
            .andExpect(status().isCreated());

        // Validate the Quarto in the database
        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeCreate + 1);
        Quarto testQuarto = quartoList.get(quartoList.size() - 1);
        assertThat(testQuarto.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testQuarto.getClasse()).isEqualTo(DEFAULT_CLASSE);
    }

    @Test
    @Transactional
    public void createQuartoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quartoRepository.findAll().size();

        // Create the Quarto with an existing ID
        quarto.setId(1L);
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(quarto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuartoMockMvc.perform(post("/api/quartos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quartoRepository.findAll().size();
        // set the field null
        quarto.setNome(null);

        // Create the Quarto, which fails.
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(quarto);

        restQuartoMockMvc.perform(post("/api/quartos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoDTO)))
            .andExpect(status().isBadRequest());

        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClasseIsRequired() throws Exception {
        int databaseSizeBeforeTest = quartoRepository.findAll().size();
        // set the field null
        quarto.setClasse(null);

        // Create the Quarto, which fails.
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(quarto);

        restQuartoMockMvc.perform(post("/api/quartos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoDTO)))
            .andExpect(status().isBadRequest());

        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuartos() throws Exception {
        // Initialize the database
        quartoRepository.saveAndFlush(quarto);

        // Get all the quartoList
        restQuartoMockMvc.perform(get("/api/quartos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quarto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].classe").value(hasItem(DEFAULT_CLASSE.toString())));
    }

    @Test
    @Transactional
    public void getQuarto() throws Exception {
        // Initialize the database
        quartoRepository.saveAndFlush(quarto);

        // Get the quarto
        restQuartoMockMvc.perform(get("/api/quartos/{id}", quarto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quarto.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.classe").value(DEFAULT_CLASSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuarto() throws Exception {
        // Get the quarto
        restQuartoMockMvc.perform(get("/api/quartos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuarto() throws Exception {
        // Initialize the database
        quartoRepository.saveAndFlush(quarto);
        int databaseSizeBeforeUpdate = quartoRepository.findAll().size();

        // Update the quarto
        Quarto updatedQuarto = quartoRepository.findOne(quarto.getId());
        updatedQuarto
            .nome(UPDATED_NOME)
            .classe(UPDATED_CLASSE);
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(updatedQuarto);

        restQuartoMockMvc.perform(put("/api/quartos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoDTO)))
            .andExpect(status().isOk());

        // Validate the Quarto in the database
        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeUpdate);
        Quarto testQuarto = quartoList.get(quartoList.size() - 1);
        assertThat(testQuarto.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testQuarto.getClasse()).isEqualTo(UPDATED_CLASSE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuarto() throws Exception {
        int databaseSizeBeforeUpdate = quartoRepository.findAll().size();

        // Create the Quarto
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(quarto);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuartoMockMvc.perform(put("/api/quartos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quartoDTO)))
            .andExpect(status().isCreated());

        // Validate the Quarto in the database
        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuarto() throws Exception {
        // Initialize the database
        quartoRepository.saveAndFlush(quarto);
        int databaseSizeBeforeDelete = quartoRepository.findAll().size();

        // Get the quarto
        restQuartoMockMvc.perform(delete("/api/quartos/{id}", quarto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Quarto> quartoList = quartoRepository.findAll();
        assertThat(quartoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quarto.class);
    }
}

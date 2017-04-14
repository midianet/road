package midianet.road.web.rest;

import com.codahale.metrics.annotation.Timed;
import midianet.road.service.PessoaService;
import midianet.road.web.rest.util.HeaderUtil;
import midianet.road.web.rest.util.PaginationUtil;
import midianet.road.service.dto.PessoaDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Pessoa.
 */
@RestController
@RequestMapping("/api")
public class PessoaResource {

    private final Logger log = LoggerFactory.getLogger(PessoaResource.class);

    private static final String ENTITY_NAME = "pessoa";

    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * POST  /pessoas : Create a new pessoa.
     *
     * @param pessoaDTO the pessoaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pessoaDTO, or with status 400 (Bad Request) if the pessoa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pessoas")
    @Timed
    public ResponseEntity<PessoaDTO> createPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) throws URISyntaxException {
        log.debug("REST request to save Pessoa : {}", pessoaDTO);
        if (pessoaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pessoa cannot already have an ID")).body(null);
        }
        PessoaDTO result = pessoaService.save(pessoaDTO);
        return ResponseEntity.created(new URI("/api/pessoas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pessoas : Updates an existing pessoa.
     *
     * @param pessoaDTO the pessoaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pessoaDTO,
     * or with status 400 (Bad Request) if the pessoaDTO is not valid,
     * or with status 500 (Internal Server Error) if the pessoaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pessoas")
    @Timed
    public ResponseEntity<PessoaDTO> updatePessoa(@Valid @RequestBody PessoaDTO pessoaDTO) throws URISyntaxException {
        log.debug("REST request to update Pessoa : {}", pessoaDTO);
        if (pessoaDTO.getId() == null) {
            return createPessoa(pessoaDTO);
        }
        PessoaDTO result = pessoaService.save(pessoaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pessoaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pessoas : get all the pessoas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pessoas in body
     */
    @GetMapping("/pessoas/list")
    @Timed
    public ResponseEntity<List<PessoaDTO>> listAllPessoas() {
        log.debug("REST request all Pessoas");
        List<PessoaDTO> page = pessoaService.listAll();
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    /**
     * GET  /pessoas : get all the pessoas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pessoas in body
     */
    @GetMapping("/pessoas")
    @Timed
    public ResponseEntity<List<PessoaDTO>> getAllPessoas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Pessoas");
        Page<PessoaDTO> page = pessoaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pessoas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pessoas/:id : get the "id" pessoa.
     *
     * @param id the id of the pessoaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pessoaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pessoas/{id}")
    @Timed
    public ResponseEntity<PessoaDTO> getPessoa(@PathVariable Long id) {
        log.debug("REST request to get Pessoa : {}", id);
        PessoaDTO pessoaDTO = pessoaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pessoaDTO));
    }

    /**
     * DELETE  /pessoas/:id : delete the "id" pessoa.
     *
     * @param id the id of the pessoaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pessoas/{id}")
    @Timed
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        log.debug("REST request to delete Pessoa : {}", id);
        pessoaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

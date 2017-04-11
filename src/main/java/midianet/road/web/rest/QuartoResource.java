package midianet.road.web.rest;

import com.codahale.metrics.annotation.Timed;
import midianet.road.service.QuartoService;
import midianet.road.web.rest.util.HeaderUtil;
import midianet.road.web.rest.util.PaginationUtil;
import midianet.road.service.dto.QuartoDTO;
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
 * REST controller for managing Quarto.
 */
@RestController
@RequestMapping("/api")
public class QuartoResource {

    private final Logger log = LoggerFactory.getLogger(QuartoResource.class);

    private static final String ENTITY_NAME = "quarto";
        
    private final QuartoService quartoService;

    public QuartoResource(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    /**
     * POST  /quartos : Create a new quarto.
     *
     * @param quartoDTO the quartoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quartoDTO, or with status 400 (Bad Request) if the quarto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quartos")
    @Timed
    public ResponseEntity<QuartoDTO> createQuarto(@Valid @RequestBody QuartoDTO quartoDTO) throws URISyntaxException {
        log.debug("REST request to save Quarto : {}", quartoDTO);
        if (quartoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new quarto cannot already have an ID")).body(null);
        }
        QuartoDTO result = quartoService.save(quartoDTO);
        return ResponseEntity.created(new URI("/api/quartos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quartos : Updates an existing quarto.
     *
     * @param quartoDTO the quartoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quartoDTO,
     * or with status 400 (Bad Request) if the quartoDTO is not valid,
     * or with status 500 (Internal Server Error) if the quartoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quartos")
    @Timed
    public ResponseEntity<QuartoDTO> updateQuarto(@Valid @RequestBody QuartoDTO quartoDTO) throws URISyntaxException {
        log.debug("REST request to update Quarto : {}", quartoDTO);
        if (quartoDTO.getId() == null) {
            return createQuarto(quartoDTO);
        }
        QuartoDTO result = quartoService.save(quartoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quartoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quartos : get all the quartos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quartos in body
     */
    @GetMapping("/quartos")
    @Timed
    public ResponseEntity<List<QuartoDTO>> getAllQuartos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Quartos");
        Page<QuartoDTO> page = quartoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quartos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quartos/:id : get the "id" quarto.
     *
     * @param id the id of the quartoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quartoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quartos/{id}")
    @Timed
    public ResponseEntity<QuartoDTO> getQuarto(@PathVariable Long id) {
        log.debug("REST request to get Quarto : {}", id);
        QuartoDTO quartoDTO = quartoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quartoDTO));
    }

    /**
     * DELETE  /quartos/:id : delete the "id" quarto.
     *
     * @param id the id of the quartoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quartos/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuarto(@PathVariable Long id) {
        log.debug("REST request to delete Quarto : {}", id);
        quartoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

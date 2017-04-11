package midianet.road.web.rest;

import com.codahale.metrics.annotation.Timed;
import midianet.road.service.QuartoTipoService;
import midianet.road.web.rest.util.HeaderUtil;
import midianet.road.web.rest.util.PaginationUtil;
import midianet.road.service.dto.QuartoTipoDTO;
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
 * REST controller for managing QuartoTipo.
 */
@RestController
@RequestMapping("/api")
public class QuartoTipoResource {

    private final Logger log = LoggerFactory.getLogger(QuartoTipoResource.class);

    private static final String ENTITY_NAME = "quartoTipo";
        
    private final QuartoTipoService quartoTipoService;

    public QuartoTipoResource(QuartoTipoService quartoTipoService) {
        this.quartoTipoService = quartoTipoService;
    }

    /**
     * POST  /quarto-tipos : Create a new quartoTipo.
     *
     * @param quartoTipoDTO the quartoTipoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quartoTipoDTO, or with status 400 (Bad Request) if the quartoTipo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quarto-tipos")
    @Timed
    public ResponseEntity<QuartoTipoDTO> createQuartoTipo(@Valid @RequestBody QuartoTipoDTO quartoTipoDTO) throws URISyntaxException {
        log.debug("REST request to save QuartoTipo : {}", quartoTipoDTO);
        if (quartoTipoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new quartoTipo cannot already have an ID")).body(null);
        }
        QuartoTipoDTO result = quartoTipoService.save(quartoTipoDTO);
        return ResponseEntity.created(new URI("/api/quarto-tipos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quarto-tipos : Updates an existing quartoTipo.
     *
     * @param quartoTipoDTO the quartoTipoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quartoTipoDTO,
     * or with status 400 (Bad Request) if the quartoTipoDTO is not valid,
     * or with status 500 (Internal Server Error) if the quartoTipoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quarto-tipos")
    @Timed
    public ResponseEntity<QuartoTipoDTO> updateQuartoTipo(@Valid @RequestBody QuartoTipoDTO quartoTipoDTO) throws URISyntaxException {
        log.debug("REST request to update QuartoTipo : {}", quartoTipoDTO);
        if (quartoTipoDTO.getId() == null) {
            return createQuartoTipo(quartoTipoDTO);
        }
        QuartoTipoDTO result = quartoTipoService.save(quartoTipoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quartoTipoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quarto-tipos : get all the quartoTipos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quartoTipos in body
     */
    @GetMapping("/quarto-tipos")
    @Timed
    public ResponseEntity<List<QuartoTipoDTO>> getAllQuartoTipos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of QuartoTipos");
        Page<QuartoTipoDTO> page = quartoTipoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quarto-tipos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quarto-tipos/:id : get the "id" quartoTipo.
     *
     * @param id the id of the quartoTipoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quartoTipoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quarto-tipos/{id}")
    @Timed
    public ResponseEntity<QuartoTipoDTO> getQuartoTipo(@PathVariable Long id) {
        log.debug("REST request to get QuartoTipo : {}", id);
        QuartoTipoDTO quartoTipoDTO = quartoTipoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quartoTipoDTO));
    }

    /**
     * DELETE  /quarto-tipos/:id : delete the "id" quartoTipo.
     *
     * @param id the id of the quartoTipoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quarto-tipos/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuartoTipo(@PathVariable Long id) {
        log.debug("REST request to delete QuartoTipo : {}", id);
        quartoTipoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

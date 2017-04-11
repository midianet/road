package midianet.road.web.rest;

import com.codahale.metrics.annotation.Timed;
import midianet.road.service.ParametroService;
import midianet.road.web.rest.util.HeaderUtil;
import midianet.road.web.rest.util.PaginationUtil;
import midianet.road.service.dto.ParametroDTO;
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
 * REST controller for managing Parametro.
 */
@RestController
@RequestMapping("/api")
public class ParametroResource {

    private final Logger log = LoggerFactory.getLogger(ParametroResource.class);

    private static final String ENTITY_NAME = "parametro";
        
    private final ParametroService parametroService;

    public ParametroResource(ParametroService parametroService) {
        this.parametroService = parametroService;
    }

    /**
     * POST  /parametros : Create a new parametro.
     *
     * @param parametroDTO the parametroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parametroDTO, or with status 400 (Bad Request) if the parametro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parametros")
    @Timed
    public ResponseEntity<ParametroDTO> createParametro(@Valid @RequestBody ParametroDTO parametroDTO) throws URISyntaxException {
        log.debug("REST request to save Parametro : {}", parametroDTO);
        if (parametroDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new parametro cannot already have an ID")).body(null);
        }
        ParametroDTO result = parametroService.save(parametroDTO);
        return ResponseEntity.created(new URI("/api/parametros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parametros : Updates an existing parametro.
     *
     * @param parametroDTO the parametroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated parametroDTO,
     * or with status 400 (Bad Request) if the parametroDTO is not valid,
     * or with status 500 (Internal Server Error) if the parametroDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parametros")
    @Timed
    public ResponseEntity<ParametroDTO> updateParametro(@Valid @RequestBody ParametroDTO parametroDTO) throws URISyntaxException {
        log.debug("REST request to update Parametro : {}", parametroDTO);
        if (parametroDTO.getId() == null) {
            return createParametro(parametroDTO);
        }
        ParametroDTO result = parametroService.save(parametroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, parametroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parametros : get all the parametros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of parametros in body
     */
    @GetMapping("/parametros")
    @Timed
    public ResponseEntity<List<ParametroDTO>> getAllParametros(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Parametros");
        Page<ParametroDTO> page = parametroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/parametros");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /parametros/:id : get the "id" parametro.
     *
     * @param id the id of the parametroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the parametroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/parametros/{id}")
    @Timed
    public ResponseEntity<ParametroDTO> getParametro(@PathVariable Long id) {
        log.debug("REST request to get Parametro : {}", id);
        ParametroDTO parametroDTO = parametroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(parametroDTO));
    }

    /**
     * DELETE  /parametros/:id : delete the "id" parametro.
     *
     * @param id the id of the parametroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parametros/{id}")
    @Timed
    public ResponseEntity<Void> deleteParametro(@PathVariable Long id) {
        log.debug("REST request to delete Parametro : {}", id);
        parametroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

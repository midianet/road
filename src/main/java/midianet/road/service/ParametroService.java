package midianet.road.service;

import midianet.road.domain.Parametro;
import midianet.road.repository.ParametroRepository;
import midianet.road.service.dto.ParametroDTO;
import midianet.road.service.mapper.ParametroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Parametro.
 */
@Service
@Transactional
public class ParametroService {

    private final Logger log = LoggerFactory.getLogger(ParametroService.class);
    
    private final ParametroRepository parametroRepository;

    private final ParametroMapper parametroMapper;

    public ParametroService(ParametroRepository parametroRepository, ParametroMapper parametroMapper) {
        this.parametroRepository = parametroRepository;
        this.parametroMapper = parametroMapper;
    }

    /**
     * Save a parametro.
     *
     * @param parametroDTO the entity to save
     * @return the persisted entity
     */
    public ParametroDTO save(ParametroDTO parametroDTO) {
        log.debug("Request to save Parametro : {}", parametroDTO);
        Parametro parametro = parametroMapper.parametroDTOToParametro(parametroDTO);
        parametro = parametroRepository.save(parametro);
        ParametroDTO result = parametroMapper.parametroToParametroDTO(parametro);
        return result;
    }

    /**
     *  Get all the parametros.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ParametroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Parametros");
        Page<Parametro> result = parametroRepository.findAll(pageable);
        return result.map(parametro -> parametroMapper.parametroToParametroDTO(parametro));
    }

    /**
     *  Get one parametro by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ParametroDTO findOne(Long id) {
        log.debug("Request to get Parametro : {}", id);
        Parametro parametro = parametroRepository.findOne(id);
        ParametroDTO parametroDTO = parametroMapper.parametroToParametroDTO(parametro);
        return parametroDTO;
    }

    /**
     *  Delete the  parametro by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Parametro : {}", id);
        parametroRepository.delete(id);
    }
}

package midianet.road.service;

import midianet.road.domain.QuartoTipo;
import midianet.road.repository.QuartoTipoRepository;
import midianet.road.service.dto.QuartoTipoDTO;
import midianet.road.service.mapper.QuartoTipoMapper;
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
 * Service Implementation for managing QuartoTipo.
 */
@Service
@Transactional
public class QuartoTipoService {

    private final Logger log = LoggerFactory.getLogger(QuartoTipoService.class);
    
    private final QuartoTipoRepository quartoTipoRepository;

    private final QuartoTipoMapper quartoTipoMapper;

    public QuartoTipoService(QuartoTipoRepository quartoTipoRepository, QuartoTipoMapper quartoTipoMapper) {
        this.quartoTipoRepository = quartoTipoRepository;
        this.quartoTipoMapper = quartoTipoMapper;
    }

    /**
     * Save a quartoTipo.
     *
     * @param quartoTipoDTO the entity to save
     * @return the persisted entity
     */
    public QuartoTipoDTO save(QuartoTipoDTO quartoTipoDTO) {
        log.debug("Request to save QuartoTipo : {}", quartoTipoDTO);
        QuartoTipo quartoTipo = quartoTipoMapper.quartoTipoDTOToQuartoTipo(quartoTipoDTO);
        quartoTipo = quartoTipoRepository.save(quartoTipo);
        QuartoTipoDTO result = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);
        return result;
    }

    /**
     *  Get all the quartoTipos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuartoTipoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuartoTipos");
        Page<QuartoTipo> result = quartoTipoRepository.findAll(pageable);
        return result.map(quartoTipo -> quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo));
    }

    /**
     *  Get one quartoTipo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public QuartoTipoDTO findOne(Long id) {
        log.debug("Request to get QuartoTipo : {}", id);
        QuartoTipo quartoTipo = quartoTipoRepository.findOne(id);
        QuartoTipoDTO quartoTipoDTO = quartoTipoMapper.quartoTipoToQuartoTipoDTO(quartoTipo);
        return quartoTipoDTO;
    }

    /**
     *  Delete the  quartoTipo by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuartoTipo : {}", id);
        quartoTipoRepository.delete(id);
    }
}

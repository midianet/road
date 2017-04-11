package midianet.road.service;

import midianet.road.domain.Quarto;
import midianet.road.repository.QuartoRepository;
import midianet.road.service.dto.QuartoDTO;
import midianet.road.service.mapper.QuartoMapper;
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
 * Service Implementation for managing Quarto.
 */
@Service
@Transactional
public class QuartoService {

    private final Logger log = LoggerFactory.getLogger(QuartoService.class);
    
    private final QuartoRepository quartoRepository;

    private final QuartoMapper quartoMapper;

    public QuartoService(QuartoRepository quartoRepository, QuartoMapper quartoMapper) {
        this.quartoRepository = quartoRepository;
        this.quartoMapper = quartoMapper;
    }

    /**
     * Save a quarto.
     *
     * @param quartoDTO the entity to save
     * @return the persisted entity
     */
    public QuartoDTO save(QuartoDTO quartoDTO) {
        log.debug("Request to save Quarto : {}", quartoDTO);
        Quarto quarto = quartoMapper.quartoDTOToQuarto(quartoDTO);
        quarto = quartoRepository.save(quarto);
        QuartoDTO result = quartoMapper.quartoToQuartoDTO(quarto);
        return result;
    }

    /**
     *  Get all the quartos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuartoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quartos");
        Page<Quarto> result = quartoRepository.findAll(pageable);
        return result.map(quarto -> quartoMapper.quartoToQuartoDTO(quarto));
    }

    /**
     *  Get one quarto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public QuartoDTO findOne(Long id) {
        log.debug("Request to get Quarto : {}", id);
        Quarto quarto = quartoRepository.findOne(id);
        QuartoDTO quartoDTO = quartoMapper.quartoToQuartoDTO(quarto);
        return quartoDTO;
    }

    /**
     *  Delete the  quarto by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Quarto : {}", id);
        quartoRepository.delete(id);
    }
}

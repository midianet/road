package midianet.road.service;

import midianet.road.domain.Pagamento;
import midianet.road.repository.PagamentoRepository;
import midianet.road.service.dto.PagamentoDTO;
import midianet.road.service.mapper.PagamentoMapper;
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
 * Service Implementation for managing Pagamento.
 */
@Service
@Transactional
public class PagamentoService {

    private final Logger log = LoggerFactory.getLogger(PagamentoService.class);
    
    private final PagamentoRepository pagamentoRepository;

    private final PagamentoMapper pagamentoMapper;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoMapper pagamentoMapper) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoMapper = pagamentoMapper;
    }

    /**
     * Save a pagamento.
     *
     * @param pagamentoDTO the entity to save
     * @return the persisted entity
     */
    public PagamentoDTO save(PagamentoDTO pagamentoDTO) {
        log.debug("Request to save Pagamento : {}", pagamentoDTO);
        Pagamento pagamento = pagamentoMapper.pagamentoDTOToPagamento(pagamentoDTO);
        pagamento = pagamentoRepository.save(pagamento);
        PagamentoDTO result = pagamentoMapper.pagamentoToPagamentoDTO(pagamento);
        return result;
    }

    /**
     *  Get all the pagamentos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PagamentoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pagamentos");
        Page<Pagamento> result = pagamentoRepository.findAll(pageable);
        return result.map(pagamento -> pagamentoMapper.pagamentoToPagamentoDTO(pagamento));
    }

    /**
     *  Get one pagamento by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PagamentoDTO findOne(Long id) {
        log.debug("Request to get Pagamento : {}", id);
        Pagamento pagamento = pagamentoRepository.findOne(id);
        PagamentoDTO pagamentoDTO = pagamentoMapper.pagamentoToPagamentoDTO(pagamento);
        return pagamentoDTO;
    }

    /**
     *  Delete the  pagamento by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Pagamento : {}", id);
        pagamentoRepository.delete(id);
    }
}

package midianet.road.service;

import midianet.road.domain.Pessoa;
import midianet.road.repository.PessoaRepository;
import midianet.road.service.dto.PessoaDTO;
import midianet.road.service.mapper.PessoaMapper;
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
 * Service Implementation for managing Pessoa.
 */
@Service
@Transactional
public class PessoaService {

    private final Logger log = LoggerFactory.getLogger(PessoaService.class);
    
    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    /**
     * Save a pessoa.
     *
     * @param pessoaDTO the entity to save
     * @return the persisted entity
     */
    public PessoaDTO save(PessoaDTO pessoaDTO) {
        log.debug("Request to save Pessoa : {}", pessoaDTO);
        Pessoa pessoa = pessoaMapper.pessoaDTOToPessoa(pessoaDTO);
        pessoa = pessoaRepository.save(pessoa);
        PessoaDTO result = pessoaMapper.pessoaToPessoaDTO(pessoa);
        return result;
    }

    /**
     *  Get all the pessoas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pessoas");
        Page<Pessoa> result = pessoaRepository.findAll(pageable);
        return result.map(pessoa -> pessoaMapper.pessoaToPessoaDTO(pessoa));
    }

    /**
     *  Get one pessoa by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PessoaDTO findOne(Long id) {
        log.debug("Request to get Pessoa : {}", id);
        Pessoa pessoa = pessoaRepository.findOne(id);
        PessoaDTO pessoaDTO = pessoaMapper.pessoaToPessoaDTO(pessoa);
        return pessoaDTO;
    }

    /**
     *  Delete the  pessoa by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Pessoa : {}", id);
        pessoaRepository.delete(id);
    }
}

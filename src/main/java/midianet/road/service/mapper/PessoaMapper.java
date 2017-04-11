package midianet.road.service.mapper;

import midianet.road.domain.*;
import midianet.road.service.dto.PessoaDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Pessoa and its DTO PessoaDTO.
 */
@Mapper(componentModel = "spring", uses = {QuartoMapper.class, })
public interface PessoaMapper {

    @Mapping(source = "quarto.id", target = "quartoId")
    @Mapping(source = "quarto.nome", target = "quartoNome")
    PessoaDTO pessoaToPessoaDTO(Pessoa pessoa);

    List<PessoaDTO> pessoasToPessoaDTOs(List<Pessoa> pessoas);

    @Mapping(source = "quartoId", target = "quarto")
    Pessoa pessoaDTOToPessoa(PessoaDTO pessoaDTO);

    List<Pessoa> pessoaDTOsToPessoas(List<PessoaDTO> pessoaDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Pessoa pessoaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        return pessoa;
    }
    

}

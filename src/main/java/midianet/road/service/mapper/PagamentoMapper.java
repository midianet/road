package midianet.road.service.mapper;

import midianet.road.domain.*;
import midianet.road.service.dto.PagamentoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Pagamento and its DTO PagamentoDTO.
 */
@Mapper(componentModel = "spring", uses = {PessoaMapper.class, })
public interface PagamentoMapper {

    @Mapping(source = "pessoa.id", target = "pessoaId")
    @Mapping(source = "pessoa.nome", target = "pessoaNome")
    PagamentoDTO pagamentoToPagamentoDTO(Pagamento pagamento);

    List<PagamentoDTO> pagamentosToPagamentoDTOs(List<Pagamento> pagamentos);

    @Mapping(source = "pessoaId", target = "pessoa")
    Pagamento pagamentoDTOToPagamento(PagamentoDTO pagamentoDTO);

    List<Pagamento> pagamentoDTOsToPagamentos(List<PagamentoDTO> pagamentoDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Pagamento pagamentoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Pagamento pagamento = new Pagamento();
        pagamento.setId(id);
        return pagamento;
    }
    

}

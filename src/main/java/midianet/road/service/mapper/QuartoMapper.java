package midianet.road.service.mapper;

import midianet.road.domain.*;
import midianet.road.service.dto.QuartoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Quarto and its DTO QuartoDTO.
 */
@Mapper(componentModel = "spring", uses = {QuartoTipoMapper.class, })
public interface QuartoMapper {

    @Mapping(source = "quartoTipo.id", target = "quartoTipoId")
    @Mapping(source = "quartoTipo.descricao", target = "quartoTipoDescricao")
    QuartoDTO quartoToQuartoDTO(Quarto quarto);

    List<QuartoDTO> quartosToQuartoDTOs(List<Quarto> quartos);

    @Mapping(source = "quartoTipoId", target = "quartoTipo")
    Quarto quartoDTOToQuarto(QuartoDTO quartoDTO);

    List<Quarto> quartoDTOsToQuartos(List<QuartoDTO> quartoDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Quarto quartoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Quarto quarto = new Quarto();
        quarto.setId(id);
        return quarto;
    }
    

}

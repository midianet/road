package midianet.road.service.mapper;

import midianet.road.domain.*;
import midianet.road.service.dto.QuartoTipoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity QuartoTipo and its DTO QuartoTipoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuartoTipoMapper {

    QuartoTipoDTO quartoTipoToQuartoTipoDTO(QuartoTipo quartoTipo);

    List<QuartoTipoDTO> quartoTiposToQuartoTipoDTOs(List<QuartoTipo> quartoTipos);

    QuartoTipo quartoTipoDTOToQuartoTipo(QuartoTipoDTO quartoTipoDTO);

    List<QuartoTipo> quartoTipoDTOsToQuartoTipos(List<QuartoTipoDTO> quartoTipoDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default QuartoTipo quartoTipoFromId(Long id) {
        if (id == null) {
            return null;
        }
        QuartoTipo quartoTipo = new QuartoTipo();
        quartoTipo.setId(id);
        return quartoTipo;
    }
    

}

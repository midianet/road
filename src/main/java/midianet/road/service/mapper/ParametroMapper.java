package midianet.road.service.mapper;

import midianet.road.domain.*;
import midianet.road.service.dto.ParametroDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Parametro and its DTO ParametroDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParametroMapper {

    ParametroDTO parametroToParametroDTO(Parametro parametro);

    List<ParametroDTO> parametrosToParametroDTOs(List<Parametro> parametros);

    Parametro parametroDTOToParametro(ParametroDTO parametroDTO);

    List<Parametro> parametroDTOsToParametros(List<ParametroDTO> parametroDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Parametro parametroFromId(Long id) {
        if (id == null) {
            return null;
        }
        Parametro parametro = new Parametro();
        parametro.setId(id);
        return parametro;
    }
    

}

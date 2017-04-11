package midianet.road.repository;

import midianet.road.domain.QuartoTipo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the QuartoTipo entity.
 */
@SuppressWarnings("unused")
public interface QuartoTipoRepository extends JpaRepository<QuartoTipo,Long> {

}

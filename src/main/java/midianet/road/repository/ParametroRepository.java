package midianet.road.repository;

import midianet.road.domain.Parametro;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Parametro entity.
 */
@SuppressWarnings("unused")
public interface ParametroRepository extends JpaRepository<Parametro,Long> {

}

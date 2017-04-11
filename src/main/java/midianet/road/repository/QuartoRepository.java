package midianet.road.repository;

import midianet.road.domain.Quarto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Quarto entity.
 */
@SuppressWarnings("unused")
public interface QuartoRepository extends JpaRepository<Quarto,Long> {

}

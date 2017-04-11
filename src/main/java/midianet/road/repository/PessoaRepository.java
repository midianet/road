package midianet.road.repository;

import midianet.road.domain.Pessoa;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Pessoa entity.
 */
@SuppressWarnings("unused")
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}

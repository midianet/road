package midianet.road.repository;

import midianet.road.domain.Pagamento;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Pagamento entity.
 */
@SuppressWarnings("unused")
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

}

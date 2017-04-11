package midianet.road.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Pagamento entity.
 */
public class PagamentoDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate data;

    private LocalDate baixa;

    @NotNull
    private BigDecimal valor;

    private Long pessoaId;

    private String pessoaNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalDate getBaixa() {
        return baixa;
    }

    public void setBaixa(LocalDate baixa) {
        this.baixa = baixa;
    }
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PagamentoDTO pagamentoDTO = (PagamentoDTO) o;

        if ( ! Objects.equals(id, pagamentoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PagamentoDTO{" +
            "id=" + id +
            ", data='" + data + "'" +
            ", baixa='" + baixa + "'" +
            ", valor='" + valor + "'" +
            '}';
    }
}

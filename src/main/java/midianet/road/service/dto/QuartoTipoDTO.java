package midianet.road.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the QuartoTipo entity.
 */
public class QuartoTipoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String descricao;

    @NotNull
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuartoTipoDTO quartoTipoDTO = (QuartoTipoDTO) o;

        if ( ! Objects.equals(id, quartoTipoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "QuartoTipoDTO{" +
            "id=" + id +
            ", descricao='" + descricao + "'" +
            ", valor='" + valor + "'" +
            '}';
    }
}

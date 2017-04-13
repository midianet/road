package midianet.road.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A QuartoTipo.
 */
@Entity
@Table(name = "quarto_tipo")
public class QuartoTipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "valor", precision=10, scale=2, nullable = false)
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

    public QuartoTipo descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public QuartoTipo valor(BigDecimal valor) {
        this.valor = valor;
        return this;
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
        QuartoTipo quartoTipo = (QuartoTipo) o;
        if (quartoTipo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, quartoTipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "QuartoTipo{" +
            "id=" + id +
            ", descricao='" + descricao + "'" +
            ", valor='" + valor + "'" +
            '}';
    }
}

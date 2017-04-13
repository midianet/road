package midianet.road.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Pagamento.
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "baixa")
    private LocalDate baixa;

    @NotNull
    @Column(name = "valor", precision=10, scale=2, nullable = false)
    private BigDecimal valor;

    @ManyToOne
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public Pagamento data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getBaixa() {
        return baixa;
    }

    public Pagamento baixa(LocalDate baixa) {
        this.baixa = baixa;
        return this;
    }

    public void setBaixa(LocalDate baixa) {
        this.baixa = baixa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Pagamento valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Pagamento pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pagamento pagamento = (Pagamento) o;
        if (pagamento.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
            "id=" + id +
            ", data='" + data + "'" +
            ", baixa='" + baixa + "'" +
            ", valor='" + valor + "'" +
            '}';
    }
}

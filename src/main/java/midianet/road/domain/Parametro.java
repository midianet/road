package midianet.road.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Parametro.
 */
@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "chave", length = 10, nullable = false)
    private String chave;

    @NotNull
    @Size(max = 20)
    @Column(name = "descricao", length = 20, nullable = false)
    private String descricao;

    @NotNull
    @Size(max = 100)
    @Column(name = "valor", length = 100, nullable = false)
    private String valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public Parametro chave(String chave) {
        this.chave = chave;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public  Parametro descricao(String descricao){
        this.descricao = descricao;
        return this;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public Parametro valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
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
        Parametro parametro = (Parametro) o;
        if (parametro.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, parametro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Parametro{" +
            "id=" + id +
            ", chave='" + chave + "'" +
            ", descricao='" + descricao + "'" +
            ", valor='" + valor + "'" +
            '}';
    }
}

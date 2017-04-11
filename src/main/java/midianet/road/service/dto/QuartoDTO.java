package midianet.road.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import midianet.road.domain.enumeration.QuartoSexo;

/**
 * A DTO for the Quarto entity.
 */
public class QuartoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private QuartoSexo classe;

    private Long quartoTipoId;

    private String quartoTipoDescricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public QuartoSexo getClasse() {
        return classe;
    }

    public void setClasse(QuartoSexo classe) {
        this.classe = classe;
    }

    public Long getQuartoTipoId() {
        return quartoTipoId;
    }

    public void setQuartoTipoId(Long quartoTipoId) {
        this.quartoTipoId = quartoTipoId;
    }

    public String getQuartoTipoDescricao() {
        return quartoTipoDescricao;
    }

    public void setQuartoTipoDescricao(String quartoTipoDescricao) {
        this.quartoTipoDescricao = quartoTipoDescricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuartoDTO quartoDTO = (QuartoDTO) o;

        if ( ! Objects.equals(id, quartoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "QuartoDTO{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", classe='" + classe + "'" +
            '}';
    }
}

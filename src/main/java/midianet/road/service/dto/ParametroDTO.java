package midianet.road.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Parametro entity.
 */
public class ParametroDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 10)
    private String chave;

    @NotNull
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Size(max = 100)
    private String valor;

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

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
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

        ParametroDTO parametroDTO = (ParametroDTO) o;

        if ( ! Objects.equals(id, parametroDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ParametroDTO{" +
            "id=" + id +
            ", chave='" + chave + "'" +
            ", descricao='" + descricao + "'" +
            ", valor='" + valor + "'" +
            '}';
    }
}

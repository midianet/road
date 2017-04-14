package midianet.road.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import midianet.road.domain.enumeration.Sexo;

/**
 * A DTO for the Pessoa entity.
 */
public class PessoaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String nome;

    private String telefone;

    @NotNull
    private Long telegram;

    private LocalDate registro;

    private Sexo sexo;

    private Integer assento;

    private Boolean confirmado;

    private Long quartoId;

    private String quartoNome;

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
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Long getTelegram() {
        return telegram;
    }

    public void setTelegram(Long telegram) {
        this.telegram = telegram;
    }
    public LocalDate getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDate registro) {
        this.registro = registro;
    }
    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public Integer getAssento() {
        return assento;
    }

    public void setAssento(Integer assento) {
        this.assento = assento;
    }
    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Long getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(Long quartoId) {
        this.quartoId = quartoId;
    }

    public String getQuartoNome() {
        return quartoNome;
    }

    public void setQuartoNome(String quartoNome) {
        this.quartoNome = quartoNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PessoaDTO pessoaDTO = (PessoaDTO) o;

        if ( ! Objects.equals(id, pessoaDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PessoaDTO{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", telefone='" + telefone + "'" +
            ", telegram='" + telegram + "'" +
            ", registro='" + registro + "'" +
            ", sexo='" + sexo + "'" +
            ", assento='" + assento + "'" +
            ", confirmado='" + confirmado + "'" +
            '}';
    }
}

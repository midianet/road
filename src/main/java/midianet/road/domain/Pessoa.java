package midianet.road.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import midianet.road.domain.enumeration.Sexo;

/**
 * A Pessoa.
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @NotNull
    @Column(name = "telegram", nullable = false)
    private Long telegram;

    @Column(name = "registro")
    private LocalDate registro;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "assento")
    private Integer assento;

    @Column(name = "confirmado")
    private Boolean confirmado;

    @ManyToOne
    private Quarto quarto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Pessoa telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getTelegram() {
        return telegram;
    }

    public Pessoa telegram(Long telegram) {
        this.telegram = telegram;
        return this;
    }

    public void setTelegram(Long telegram) {
        this.telegram = telegram;
    }

    public LocalDate getRegistro() {
        return registro;
    }

    public Pessoa registro(LocalDate registro) {
        this.registro = registro;
        return this;
    }

    public void setRegistro(LocalDate registro) {
        this.registro = registro;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Pessoa sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getAssento() {
        return assento;
    }

    public Pessoa assento(Integer assento) {
        this.assento = assento;
        return this;
    }

    public void setAssento(Integer assento) {
        this.assento = assento;
    }

    public Boolean isConfirmado() {
        return confirmado;
    }

    public Pessoa confirmado(Boolean confirmado) {
        this.confirmado = confirmado;
        return this;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Pessoa quarto(Quarto quarto) {
        this.quarto = quarto;
        return this;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        if (pessoa.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
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

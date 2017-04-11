package midianet.road.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import midianet.road.domain.enumeration.QuartoSexo;

/**
 * A Quarto.
 */
@Entity
@Table(name = "quarto")
public class Quarto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "classe", nullable = false)
    private QuartoSexo classe;

    @ManyToOne
    private QuartoTipo quartoTipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Quarto nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public QuartoSexo getClasse() {
        return classe;
    }

    public Quarto classe(QuartoSexo classe) {
        this.classe = classe;
        return this;
    }

    public void setClasse(QuartoSexo classe) {
        this.classe = classe;
    }

    public QuartoTipo getQuartoTipo() {
        return quartoTipo;
    }

    public Quarto quartoTipo(QuartoTipo quartoTipo) {
        this.quartoTipo = quartoTipo;
        return this;
    }

    public void setQuartoTipo(QuartoTipo quartoTipo) {
        this.quartoTipo = quartoTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quarto quarto = (Quarto) o;
        if (quarto.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, quarto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Quarto{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", classe='" + classe + "'" +
            '}';
    }
}

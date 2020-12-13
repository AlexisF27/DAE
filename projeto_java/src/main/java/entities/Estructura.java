package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ESTRUCTURAS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NOME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllEstructuras",
                query = "SELECT e FROM Estructura e ORDER BY e.nome" // JPQL
        ) })
public class Estructura {

    @Id
    @NotNull
    private String nome;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEstructura tipoMaterial;
    @NotNull
    private int nb;
    @NotNull
    private double LVao;
    @NotNull
    private int q;
    @ManyToMany
    @JoinTable(name = "ESTRUCTURA_VARIANTE",
            joinColumns = @JoinColumn(name = "VARIANTE_CODIGO", referencedColumnName = "CODIGO"),
            inverseJoinColumns = @JoinColumn(name = "ESTRUCTURA_NOME", referencedColumnName = "NOME"))
    Set<Variante> variantes;
    @ManyToOne
    @JoinColumn(name = "PROJETO_CODE")
    @NotNull
    protected Projeto projeto;
    @Version
    private int version;


    public Estructura() {
        this.variantes = new HashSet<>();
    }

    public Estructura(@NotNull String nome, @NotNull TipoEstructura tipoMaterial, @NotNull int nb, @NotNull double LVao, @NotNull int q,@NotNull Projeto projeto) {
        this.nome = nome;
        this.tipoMaterial = tipoMaterial;
        this.nb = nb;
        this.LVao = LVao;
        this.q = q;
        this.variantes = new HashSet<>();
        this.projeto = projeto;
    }

    @Override
    public String toString() {
        return "Estructura{" +
                "nome='" + nome + '\'' +
                ", tipoMaterial='" + tipoMaterial + '\'' +
                ", nb=" + nb +
                ", LVao=" + LVao +
                ", q=" + q +
                ", variantes=" + variantes +
                ", projeto=" + projeto +
                ", version=" + version +
                '}';
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public double getLVao() {
        return LVao;
    }

    public void setLVao(double LVao) {
        this.LVao = LVao;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public TipoEstructura getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoEstructura tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public Set<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(Set<Variante> variantes) {
        this.variantes = variantes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

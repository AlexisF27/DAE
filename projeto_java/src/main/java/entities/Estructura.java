package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Estructura {

    @Id
    @NotNull
    private String nome;
    @NotNull
    private String tipoMaterial;
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

    public Estructura() {
        this.variantes = new HashSet<>();
    }

    public Estructura(@NotNull String nome, @NotNull String tipoMaterial, @NotNull int nb, @NotNull double LVao, @NotNull int q) {
        this.nome = nome;
        this.tipoMaterial = tipoMaterial;
        this.nb = nb;
        this.LVao = LVao;
        this.q = q;
        this.variantes = new HashSet<>();
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

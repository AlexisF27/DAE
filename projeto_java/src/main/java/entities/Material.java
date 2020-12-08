package entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="MATERIALES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NOME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllMateriales",
                query = "SELECT m FROM Material m ORDER BY m.nome" // JPQL
        ) })
public class Material {
    @Id
    private String nome;

    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE)
    private List<Variante> variantes;

    public Material() {
        variantes = new LinkedList<>();
    }

    public Material(String nome) {
        this.nome = nome;
        variantes = new LinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public List<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<Variante> especimen) {
        this.variantes = especimen;
    }

    public void addVariante(Variante s) {
        variantes.add(s);
    }

    public void removeVariante(Variante s) {
        variantes.remove(s);
    }
}

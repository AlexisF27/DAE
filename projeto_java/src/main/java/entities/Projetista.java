package entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="PROJETISTAS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ID","MAIL"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllProjetistas",
                query = "SELECT p FROM Projetista p ORDER BY 1" // JPQL
        ) })

public class Projetista extends User implements Serializable {

    @OneToMany(mappedBy = "projetista", cascade = CascadeType.REMOVE)
    private Set<Projeto> projetos;
    @Version
    private int version;

    public Projetista() {
    }

    public Projetista(int id, @NotNull String nome, @Email @NotNull String mail) {
        super(id,nome, mail);
        projetos = new HashSet<>();
    }


    public void addProjetos(Projeto projeto){
        projetos.add(projeto);
    }
    public void removeProjeto(Projeto projeto){
        projetos.remove(projeto);
    }


    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

}
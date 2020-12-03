package entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="PROJETISTAS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME","MAIL"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllProjetistas",
                query = "SELECT p FROM Projetista p ORDER BY 1" // JPQL
        ) })

public class Projetista {

    @Id
    @Basic(optional = false ,fetch = FetchType.LAZY)
    private int id;
    @NotNull
    @Basic(optional = false ,fetch = FetchType.LAZY)
    protected String name;
    @Email
    @NotNull
    @Basic(optional = false ,fetch = FetchType.LAZY)
    protected String mail;
    @OneToMany(mappedBy = "projetista", cascade = CascadeType.REMOVE)
    private Set<Projeto> projetos;

    public Projetista() {
    }

    public Projetista(int id, @NotNull String name, @Email @NotNull String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        projetos = new HashSet<>();
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
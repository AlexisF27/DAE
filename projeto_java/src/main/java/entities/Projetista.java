package entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProjetistas",
                query = "SELECT p FROM Projetista p ORDER BY 1" // JPQL
        ) })

public class Projetista {

    @Id
    private String id;
    @NotNull
    protected String name;
    @Email
    @NotNull
    protected String mail;

    public Projetista() {
    }

    public Projetista(String id, @NotNull String name, @Email @NotNull String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

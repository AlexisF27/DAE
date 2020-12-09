package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="FABRICANTES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ID","MAIL"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllFabricantes",
                query = "SELECT f FROM Fabricante f ORDER BY 1" // JPQL
        ) })

public class Fabricante {
    @Id
    private int id;
    @NotNull
    protected String name;
    @Email
    @NotNull String mail;
    @Version
    private int version;

    public Fabricante() {

    }

    public Fabricante(int id, @NotNull String name, @Email @NotNull String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

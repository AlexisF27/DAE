package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="id")
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    protected int id;
    @NotNull
    protected String nome;
    @Email
    @NotNull
    protected String email;
    @Version
    private int version;

    public User() {
    }

    public User(int id, @NotNull String nome, @Email @NotNull String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

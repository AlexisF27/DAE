package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Array;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllClientes",
                query = "SELECT c FROM Cliente c ORDER BY c.nome" // JPQL
        ) })

public class Cliente implements Serializable {
    @Id
    protected String id;
    @NotNull
    protected String nome;
    @NotNull
    protected String morada;
    @Email
    @NotNull
    protected String mail;

    @NotNull
    protected String pessoaContacto;

    public Cliente() {

    }

    public Cliente(String id, @NotNull String nome, @NotNull String morada, @Email @NotNull String mail, @NotNull String pessoaContacto) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.mail = mail;
        this.pessoaContacto = pessoaContacto;
    }



    public String getPessoaContacto() {
        return pessoaContacto;
    }

    public void setPessoaContacto(String pessoaContacto) {
        this.pessoaContacto = pessoaContacto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

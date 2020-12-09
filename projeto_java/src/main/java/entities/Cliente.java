package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.Provider;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="CLIENTES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ID","MAIL"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllClientes",
                query = "SELECT c FROM Cliente c ORDER BY c.nome" // JPQL
        ) })

public class Cliente implements Serializable {
    @Id
    protected int id;
    @NotNull
    protected String nome;
    @NotNull
    protected String morada;
    @Email
    @NotNull
    protected String mail;
    @Version
    private int version;
    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride( name = "nome", column = @Column(name = "nome_pessoa_contacto")),
            @AttributeOverride( name = "email", column = @Column(name = "email_pessoa_contacto")),
            @AttributeOverride( name = "telefone", column = @Column(name = "telefone_pessoa_contacto"))
    })
    protected PessoaContacto pessoaContacto;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    private Set<Projeto> projetos;

    public Cliente() {

    }

    public Cliente(int id, @NotNull String nome, @NotNull String morada, @Email @NotNull String mail,@NotNull PessoaContacto pessoaContacto) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.mail = mail;
        this.pessoaContacto = pessoaContacto;
        projetos = new HashSet<>();
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjetos(Projeto projeto){
        projetos.add(projeto);
    }

    public void removeProjeto(Projeto projeto){
        projetos.remove(projeto);
    }

    public PessoaContacto getPessoaContacto() {
        return pessoaContacto;
    }

    public void setPessoaContacto(PessoaContacto pessoaContacto) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

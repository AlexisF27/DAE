package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity

@Table(
        name = "PROJETOS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NOME","CLIENTE_CODE","PROJETISTA_CODE"})
)

@NamedQueries({
        @NamedQuery(
                name = "getAllProjetos",
                query = "SELECT p FROM Projeto p ORDER BY p.cliente.id , p.projetista.id ,p.nome" // JPQL
        )
})

public class Projeto implements Serializable {
    @Id
    @Basic(optional = false ,fetch = FetchType.EAGER)
    protected int id;
    @NotNull
    @Basic(optional = false ,fetch = FetchType.EAGER)
    protected String nome;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_CODE")
    @NotNull
    protected Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "PROJETISTA_CODE")
    @NotNull
    protected Projetista projetista;


    public Projeto(){

    }

    public Projeto(int id, @NotNull String nome, @NotNull Cliente cliente, @NotNull Projetista projetista) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        this.projetista = projetista;
    }


    public Projetista getProjetista() {
        return projetista;
    }

    public void setProjetista(Projetista projetista) {
        this.projetista = projetista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}

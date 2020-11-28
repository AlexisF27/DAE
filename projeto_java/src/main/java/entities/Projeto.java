package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity

@Table(
        name = "PROJETOS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NOME","CLIENTE_CODE"})
)

@NamedQueries({
        @NamedQuery(
                name = "getAllProjetos",
                query = "SELECT p FROM Projeto p ORDER BY p.cliente.id ,p.nome" // JPQL
        )
})

public class Projeto implements Serializable {
    @Id
    protected int id;
    @NotNull
    protected String nome;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_CODE")
    @NotNull
    protected Cliente cliente;

    public Projeto(){

    }

    public Projeto(int id, @NotNull String nome, @NotNull Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
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

package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(
        name = "PROJETOS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NOME","CLIENTE_CODE","PROJETISTA_CODE"})
)

@NamedQueries({
        @NamedQuery(
                name = "getAllProjetos",
                query = "SELECT p FROM Projeto p ORDER BY p.cliente.username" // JPQL
        )
})

public class Projeto implements Serializable {
    @Id
    @Basic(optional = false ,fetch = FetchType.EAGER)
    protected int id;
    @NotNull
    protected String nome;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_CODE")
    protected Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "PROJETISTA_CODE")
    @NotNull
    protected Projetista projetista;
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
    private Set<Estructura> estructuras;
    protected int version;


    public Projeto(){

    }

    public Projeto(int id, @NotNull String nome,  Cliente cliente, @NotNull Projetista projetista) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        this.projetista = projetista;
        estructuras = new HashSet<>();

    }

    public Set<Estructura> getEstructuras() {
        return estructuras;
    }

    public void setEstructuras(Set<Estructura> estructuras) {
        this.estructuras = estructuras;
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

    public void addEstructuras(Estructura estructura) {
        if(estructuras.contains(estructura) &&estructura == null){
            return;
        }
        estructuras.add(estructura);
    }

    public void removeEstrucutras(Estructura estructura){
        if (!estructuras.contains(estructura) && estructura == null){
            return;
        }
        estructuras.remove(estructura);
    }
}

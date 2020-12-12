package entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Projetista extends User implements Serializable {

    @OneToMany(mappedBy = "projetista", cascade = CascadeType.REMOVE)
    private Set<Projeto> projetos;
    @Version
    private int version;

    public Projetista() {
    }

    public Projetista(String username, String password, @NotNull String nome, @Email @NotNull String mail) {
        super(username,password,nome, mail);
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
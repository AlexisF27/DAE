package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fabricante extends User implements Serializable {


    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.REMOVE)
    private Set<Material> materiales;





    @Version
    private int version;

    public Fabricante() {

    }

    public Fabricante(String username,@NotNull String password ,@NotNull String name, @Email @NotNull String mail) {
        super(username,name,password,mail);
        materiales = new HashSet<>();
    }

    public void addMateriales(Material material){
        if(materiales.contains(material) && material == null){
            return;
        }
        materiales.add(material);
    }

    public void removeMaterial(Material material){
        if(!materiales.contains(material) && material == null)
            materiales.remove(material);
    }




    public Set<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(Set<Material> materiales) {
        this.materiales = materiales;
    }

}

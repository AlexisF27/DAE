package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Fabricante extends User implements Serializable {
    @Version
    private int version;

    public Fabricante() {

    }

    public Fabricante(String username,@NotNull String password ,@NotNull String name, @Email @NotNull String mail) {
        super(username,name,password,mail);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

package entities;

import javax.persistence.Embeddable;

@Embeddable
public class PessoaContacto {
    private String nome;
    private String email;
    private long telefone;

    public PessoaContacto() {

    }
    public PessoaContacto(String nome, String email, long telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
}

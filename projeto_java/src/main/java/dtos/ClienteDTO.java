package dtos;

import entities.PessoaContacto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private String username;
    private String nome;
    private String morada;
    private String password;
    private String mail;
    private PessoaContacto pessoaContacto;

    public ClienteDTO() {
    }

    public ClienteDTO(String username, String nome,String password, String morada, String mail, PessoaContacto pessoaContacto) {
        this.username = username;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.mail = mail;
        this.pessoaContacto = pessoaContacto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public PessoaContacto getPessoaContacto() {
        return pessoaContacto;
    }

    public void setPessoaContacto(PessoaContacto pessoaContacto) {
        this.pessoaContacto = pessoaContacto;
    }
}

package dtos;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private String id;
    private String nome;
    private String morada;
    private String mail;
    private double pessoaContacto;

    public ClienteDTO() {
    }

    public ClienteDTO(String id, String nome, String morada, String mail, double pessoaContacto) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.mail = mail;
        this.pessoaContacto = pessoaContacto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getPessoaContacto() {
        return pessoaContacto;
    }

    public void setPessoaContacto(double pessoaContacto) {
        this.pessoaContacto = pessoaContacto;
    }
}

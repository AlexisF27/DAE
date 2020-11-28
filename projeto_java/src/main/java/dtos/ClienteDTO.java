package dtos;

import entities.PessoaContacto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private int id;
    private String nome;
    private String morada;
    private String mail;
    private PessoaContacto pessoaContacto;

    public ClienteDTO() {
    }

    public ClienteDTO(int id, String nome, String morada, String mail, PessoaContacto pessoaContacto) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.mail = mail;
        this.pessoaContacto = pessoaContacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public PessoaContacto getPessoaContacto() {
        return pessoaContacto;
    }

    public void setPessoaContacto(PessoaContacto pessoaContacto) {
        this.pessoaContacto = pessoaContacto;
    }
}

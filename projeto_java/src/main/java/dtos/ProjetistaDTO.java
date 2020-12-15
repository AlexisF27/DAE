package dtos;

import entities.Projeto;

import java.util.LinkedList;
import java.util.List;

public class ProjetistaDTO {
    private String username;
    private String nome;
    private String mail;
    private List<Projeto> projetos;

    public ProjetistaDTO() {
        projetos = new LinkedList<>();
    }

    public ProjetistaDTO(String username, String nome, String mail) {
        this.username = username;
        this.nome = nome;
        this.mail = mail;
        projetos = new LinkedList<>();
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}
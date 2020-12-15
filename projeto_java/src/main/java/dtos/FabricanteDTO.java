package dtos;

import entities.Material;

import java.util.LinkedList;
import java.util.List;

public class FabricanteDTO {
    private String username;
    private String nome;
    private String mail;
    private List<Material> materials;

    public FabricanteDTO(String username, String nome, String mail) {
        this.username = username;
        this.nome = nome;
        this.mail = mail;
        materials = new LinkedList<>();
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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}

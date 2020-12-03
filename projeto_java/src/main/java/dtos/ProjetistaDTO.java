package dtos;

public class ProjetistaDTO {
    private int id;
    private String nome;
    private String mail;

    public ProjetistaDTO() {
    }

    public ProjetistaDTO(int id, String nome, String mail) {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
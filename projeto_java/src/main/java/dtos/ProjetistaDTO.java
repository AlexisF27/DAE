package dtos;

public class ProjetistaDTO {
    private String id;
    private String nome;
    private String mail;

    public ProjetistaDTO() {
    }

    public ProjetistaDTO(String id, String nome, String mail) {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

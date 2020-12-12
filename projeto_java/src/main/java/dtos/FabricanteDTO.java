package dtos;

public class FabricanteDTO {
    private String username;
    private String nome;
    private String mail;

    public FabricanteDTO(String username, String nome, String mail) {
        this.username = username;
        this.nome = nome;
        this.mail = mail;
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
}

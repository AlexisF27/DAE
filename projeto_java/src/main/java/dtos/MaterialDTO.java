package dtos;

public class MaterialDTO {
    private String nome;

    public MaterialDTO(){}

    public MaterialDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

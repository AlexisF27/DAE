package dtos;

public class MaterialDTO {
    private String nome;
    private String fabricante;

    public MaterialDTO(){}

    public MaterialDTO(String nome,String fabricante) {
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

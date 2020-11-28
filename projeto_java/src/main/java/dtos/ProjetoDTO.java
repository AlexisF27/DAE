package dtos;

public class ProjetoDTO {
    private int id;
    private String nome;
    private int clienteCode;

    public ProjetoDTO(){

    }

    public ProjetoDTO(int id, String nome, int clienteCode) {
        this.id = id;
        this.nome = nome;
        this.clienteCode = clienteCode;
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

    public int getClienteCode() {
        return clienteCode;
    }

    public void setClienteCode(int clienteCode) {
        this.clienteCode = clienteCode;
    }
}

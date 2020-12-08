package dtos;

import java.util.HashSet;
import java.util.Set;

public class ProjetoDTO {
    private int id;
    private String nome;
    private int clienteCode;
    private int projetistaCode;
    private Set<EstructuraDTO> estructuras;



    public ProjetoDTO(){
        estructuras = new HashSet<>();

    }

    public ProjetoDTO(int id, String nome, int clienteCode,int projetistaCode) {
        this.id = id;
        this.nome = nome;
        this.clienteCode = clienteCode;
        this.projetistaCode = projetistaCode;
        estructuras = new HashSet<>();
    }

    public Set<EstructuraDTO> getEstructuras() {
        return estructuras;
    }

    public void setEstructuras(Set<EstructuraDTO> estructuras) {
        this.estructuras = estructuras;
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
    public int getProjetistaCode() {
        return projetistaCode;
    }

    public void setProjetistaCode(int projetistaCode) {
        this.projetistaCode = projetistaCode;
    }
}

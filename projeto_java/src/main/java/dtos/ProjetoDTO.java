package dtos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ProjetoDTO {
    private int id;
    private String nome;
    private String clienteCode;
    private String projetistaCode;
    private List<EstructuraDTO> estructuras;



    public ProjetoDTO(){

    }

    public ProjetoDTO(int id, String nome, String clienteCode,String projetistaCode) {
        this.id = id;
        this.nome = nome;
        this.clienteCode = clienteCode;
        this.projetistaCode = projetistaCode;
        estructuras = new LinkedList<>();
    }

    public List<EstructuraDTO> getEstructuras() {
        return estructuras;
    }

    public void setEstructuras(List<EstructuraDTO> estructuras) {
        this.estructuras = estructuras;
    }

    @Override
    public String toString() {
        return "ProjetoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", clienteCode='" + clienteCode + '\'' +
                ", projetistaCode='" + projetistaCode + '\'' +
                ", estructuras=" + estructuras +
                '}';
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


    public String getClienteCode() {
        return clienteCode;
    }

    public void setClienteCode(String clienteCode) {
        this.clienteCode = clienteCode;
    }

    public String getProjetistaCode() {
        return projetistaCode;
    }

    public void setProjetistaCode(String projetistaCode) {
        this.projetistaCode = projetistaCode;
    }
}

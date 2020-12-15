package entities;

import javax.persistence.*;


@Entity
@Table(name = "DOCUMENTS")
@NamedQuery(
        name = "getClientesDocumentos",
        query = "SELECT doc FROM Documento doc WHERE doc.cliente.username = :username")

public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String filepath;
    private String filename;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Cliente cliente;

    public Documento() {
    }

    public Documento(String filepath, String filename, Cliente cliente) {
        this.filepath = filepath;
        this.filename = filename;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

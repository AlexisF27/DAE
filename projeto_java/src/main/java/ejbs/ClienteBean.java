package ejbs;

import dtos.ClienteDTO;
import entities.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import java.util.List;

@Stateless
public class ClienteBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome,String morada, String mail, String pessoaContacto){
        Cliente cliente = em.find(Cliente.class,id);
        cliente = new Cliente(id,nome,morada,mail,pessoaContacto);
        em.persist(cliente);
    }

    @GET
    // means: to call this endpoint, we need to use the HTTP GET method @Path("/") // means: the relative url path is “/api/students/”
    public List<Cliente> getAllClientes() {
// remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return  em.createNamedQuery("getAllClientes", Cliente.class).getResultList();
    }
}

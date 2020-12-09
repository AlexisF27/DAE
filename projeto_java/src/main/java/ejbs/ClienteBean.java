package ejbs;

import dtos.ClienteDTO;
import entities.Cliente;
import entities.PessoaContacto;
import entities.Projetista;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import java.util.List;

@Stateless
public class ClienteBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome,String morada, String mail, PessoaContacto pessoaContacto) throws MyEntityExistsException {
        Cliente cliente = em.find(Cliente.class,id);

        if(cliente != null){
            throw new MyEntityExistsException("Cliente con id: "+id+"ja existe");
        }

        if(cliente == null){
            throw new MyEntityExistsException("O cliente ja foi inserido");
        }
        em.persist(cliente);
    }

    @GET
    // means: to call this endpoint, we need to use the HTTP GET method @Path("/") // means: the relative url path is “/api/students/”
    public List<Cliente> getAllClientes() {
        try {
            return  em.createNamedQuery("getAllClientes", Cliente.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_CLIENTES", e);
        }

    }
}

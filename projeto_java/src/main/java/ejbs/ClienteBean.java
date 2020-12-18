package ejbs;

import dtos.ClienteDTO;
import entities.Cliente;
import entities.PessoaContacto;
import entities.Projetista;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import java.util.List;

@Stateless
public class    ClienteBean {
    @PersistenceContext
    EntityManager em;

    public void create(String username, String nome,String morada,String password, String mail, PessoaContacto pessoaContacto)
            throws MyEntityExistsException,MyConstraintViolationException {
        Cliente cliente = em.find(Cliente.class,username);

        if(cliente != null){
            throw new MyEntityExistsException("Cliente con id: "+username+"ja existe");
        }

        try {
            cliente = new Cliente(username,nome,password,morada,mail,pessoaContacto);
            em.persist(cliente);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public Cliente findCliente(String username){
        return em.find(Cliente.class,username);
    }

}

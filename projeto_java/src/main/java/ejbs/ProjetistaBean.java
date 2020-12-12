package ejbs;

import entities.Cliente;
import entities.Projetista;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class ProjetistaBean {
    @PersistenceContext
    EntityManager em;

    public void create(String username, String password,String name, String mail) throws MyEntityExistsException,MyConstraintViolationException {
        Projetista projetista = em.find(Projetista.class,username);
        if(projetista != null){
            throw new MyEntityExistsException("O projetista ja foi criado");
        }
        try{
        projetista = new Projetista(username,password,name,mail);
        em.persist(projetista);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }


}
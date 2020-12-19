package ejbs;

import entities.Fabricante;
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
public class FabricanteBean {
    @PersistenceContext
    EntityManager em;
    public void create(String username, String name,String password, String mail) throws MyEntityExistsException, MyConstraintViolationException {
        Fabricante fabricante = em.find(Fabricante.class,username);
        if(fabricante != null){
            throw new MyEntityExistsException("O fabricante "+username+" ja foi criado");
        }
        try{
            fabricante = new Fabricante(username,name,password,mail);
            em.persist(fabricante);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public Fabricante findFabricante(String username){
        return em.find(Fabricante.class, username);
    }





}

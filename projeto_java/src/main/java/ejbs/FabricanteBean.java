package ejbs;

import entities.Fabricante;
import entities.Projetista;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

public class FabricanteBean {
    @PersistenceContext
    EntityManager em;
    public void create(int id, String name, String mail) throws MyEntityExistsException, MyConstraintViolationException {
        Fabricante fabricante = em.find(Fabricante.class,id);
        if(fabricante != null){
            throw new MyEntityExistsException("O fabricante ja foi criado");
        }
        try{
            fabricante = new Fabricante(id,name,mail);
            em.persist(fabricante);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public List<Fabricante> getAllFabricante() {
        try {
            return em.createNamedQuery("getAllFabricantes", Fabricante.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_FABRICANTES", e);
        }
    }


}

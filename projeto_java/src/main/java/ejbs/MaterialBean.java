package ejbs;

import entities.Material;
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
public class MaterialBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(String name) throws MyEntityExistsException, MyConstraintViolationException {
        Material material = entityManager.find(Material.class,name);
        if(material != null) {
            throw new MyEntityExistsException("O material ja foi criado ");
        }
        try {
            material = new Material(name);
            entityManager.persist(material);
        }catch(ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public List<Material> getAllMateriales() {
        try {
            return entityManager.createNamedQuery("getAllMateriales", Material.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_MATERIALES", e);
        }

    }
}

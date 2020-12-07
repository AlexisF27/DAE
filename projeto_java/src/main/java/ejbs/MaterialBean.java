package ejbs;

import entities.Material;
import entities.Projetista;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MaterialBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(String name) throws MyEntityExistsException {
        Material material = new Material(name);
        if(material == null) {
            throw new MyEntityExistsException("O material ja foi criado ");
        }
        entityManager.persist(material);
    }

    public List<Material> getAllMateriales() {
        try {
            return entityManager.createNamedQuery("getAllMateriales", Material.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_MATERIALES", e);
        }

    }
}

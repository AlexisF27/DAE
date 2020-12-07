package ejbs;

import entities.Material;
import exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}

package ejbs;

import entities.Material;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MaterialBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(String name){
        Material material = new Material(name);
        entityManager.persist(material);
    }
}

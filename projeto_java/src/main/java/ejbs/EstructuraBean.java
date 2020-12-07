package ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstructuraBean {
    @PersistenceContext
    EntityManager entityManager;

    public void Create(String nome){

    }
}

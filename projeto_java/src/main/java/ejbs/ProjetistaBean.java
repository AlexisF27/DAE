package ejbs;

import entities.Cliente;
import entities.Projetista;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjetistaBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String name, String mail) throws MyEntityExistsException {
        Projetista projetista = new Projetista(id,name,mail);
        if(projetista == null){
            throw new MyEntityExistsException("O projetista ja foi criado");
        }
        em.persist(projetista);
    }

    public List<Projetista> all() {
        try {
            return em.createNamedQuery("getAllProjetistas", Projetista.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_STUDENTS", e);
        }

    }

}
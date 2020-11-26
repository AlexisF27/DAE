package ejbs;

import entities.Cliente;
import entities.Projetista;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjetistaBean {
    @PersistenceContext
    EntityManager em;

    public void create(String id, String name, String mail){
        Projetista projetista = em.find(Projetista.class,id);
        projetista = new Projetista(id,name,mail);
        em.persist(projetista);
    }

    public List<Projetista> all() {
        try {
            // remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
            return (List<Projetista>)
                    em.createNamedQuery("getAllProjetistas", Projetista.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_STUDENTS", e);
        }

    }

}

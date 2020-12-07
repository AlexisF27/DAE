package ejbs;

import entities.Material;
import entities.Projetista;
import entities.Variante;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VarianteBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(int codigo, String nomeProduto, String name, double weff_p, double weff_n, double ar, double sigmaC) throws MyEntityExistsException {
        Material material = entityManager.find(Material.class, nomeProduto);
        Variante variante = new Variante(codigo,material,name,weff_p,weff_n,ar,sigmaC);
        if(variante == null){
            throw  new MyEntityExistsException("A variante ja foi criada");
        }
        entityManager.persist(variante);
    }

    public List<Variante> getAllVariantes() {
        try {
            return entityManager.createNamedQuery("getAllVariantes", Variante.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_VARIANTES", e);
        }

    }

}

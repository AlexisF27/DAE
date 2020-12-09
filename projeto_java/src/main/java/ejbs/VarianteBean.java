package ejbs;

import entities.Material;
import entities.Projetista;
import entities.Variante;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class VarianteBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(int codigo, String nomeProduto, String name, double weff_p, double weff_n, double ar, double sigmaC)
            throws MyEntityExistsException,MyEntityNotFoundException, MyConstraintViolationException {
        Material material = entityManager.find(Material.class, nomeProduto);

        if(material != null){
            throw  new MyEntityNotFoundException("A variante:"+ codigo +" ja foi criada");
        }
        Variante variante = entityManager.find(Variante.class, codigo);
        if(variante == null){
            throw new MyEntityExistsException("A variante ja foi criada");
        }
        try{
            variante = new Variante(codigo,material,name,weff_p,weff_n,ar,sigmaC);
            entityManager.persist(variante);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public List<Variante> getAllVariantes() {
        try {
            return entityManager.createNamedQuery("getAllVariantes", Variante.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_VARIANTES", e);
        }

    }

}

package ejbs;

import entities.Estructura;
import entities.Projetista;
import entities.Projeto;
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
public class EstructuraBean {
    @PersistenceContext
    EntityManager entityManager;

    public void create(String nome, String tipoMaterial, int nb, double LVao, int q, int projetoCode) throws MyEntityExistsException, MyEntityNotFoundException,MyConstraintViolationException {
        try{
            Estructura estructura = entityManager.find(Estructura.class,nome);
            //System.out.println("ESTRUCTURA"+estructura.toString());
            Projeto projeto = entityManager.find(Projeto.class,projetoCode);
            //System.out.println("ESTRUCTURA"+estructura.toString());
            estructura = new Estructura(nome,tipoMaterial,nb,LVao,q,projeto);
            entityManager.persist(estructura);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }




    }
    public List<Estructura> getAllEstructuras() {
        try {
            return entityManager.createNamedQuery("getAllEstrucutras", Estructura.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_ESTRUCTURAS", e);
        }

    }

}

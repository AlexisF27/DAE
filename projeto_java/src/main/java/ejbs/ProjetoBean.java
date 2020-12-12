package ejbs;

import entities.Cliente;
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
public class ProjetoBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome,  String projetistaCode) throws MyEntityExistsException,MyConstraintViolationException,MyEntityNotFoundException {
        try {
            Projetista projetista = em.find(Projetista.class, projetistaCode );
            Projeto projeto = em.find(Projeto.class,id);
            projeto = new Projeto(id, nome,null, projetista);
            em.persist(projeto);
            projetista.addProjetos(projeto);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }

    }

    public List<Projeto> getAllProjetos() {
        try {
            return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
        } catch(Exception exception){
            throw new EJBException("ERROR_RETRIEVING_PROJETOS",exception);
        }
    }

    public void enrollsProjetoInProjetista(int id, String projetistaCode){
        Projeto projeto = em.find(Projeto.class,id);
        if(projeto == null){
            throw new IllegalArgumentException();
        }
        Projetista projetista = em.find(Projetista.class,projetistaCode);
        if(projetista == null){
            throw new IllegalArgumentException();
        }
            projetista.addProjetos(projeto);
            projeto.setProjetista(projetista);
    }

}

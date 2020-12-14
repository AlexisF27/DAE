package ejbs;

import entities.*;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class ProjetoBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome,  String projetistaCode) throws MyEntityExistsException,MyConstraintViolationException,MyEntityNotFoundException {
        try {
            Projetista projetista = em.find(Projetista.class, projetistaCode);
            if (projetista == null){
                throw new MyEntityNotFoundException("O projetista asociado nao foi encontrado");
            }
            Projeto projeto = findProjeto(id);
            if (projeto != null){
                throw  new MyEntityExistsException("O projeto ja existe");
            }
            projeto = new Projeto(id, nome,null, projetista);
            em.persist(projeto);
            projetista.addProjetos(projeto);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }

    }

    public void updateProjeto(int id, String nome, String projetistaCode) throws MyEntityNotFoundException{
        Projeto projeto =  findProjeto(id);
        if(projeto == null) {
            throw new MyEntityNotFoundException("Projeto com id " + id + " nao encontrada.");
        }
        Projetista projetista = em.find(Projetista.class, projetistaCode);
        if(projetista == null) {
            throw new MyEntityNotFoundException("Projetista com code " + projetistaCode + " not found.");
        }

        em.lock(projeto, LockModeType.OPTIMISTIC);
        projeto.setNome(nome);
        projeto.setProjetista(projetista);  /////implementar unroll

        em.merge(projeto);

    }


    public Projeto findProjeto(int id){
        return em.find(Projeto.class, id);
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

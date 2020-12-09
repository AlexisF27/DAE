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

    public void create(int id, String nome, int clienteCode, int projetistaCode) throws MyEntityExistsException,MyConstraintViolationException,MyEntityNotFoundException {
        Cliente cliente = em.find(Cliente.class, clienteCode);
        Projetista projetista = em.find(Projetista.class, projetistaCode );
        if(cliente != null && projetista != null) {
            throw new MyEntityNotFoundException("Cliente com o id: " + clienteCode + " ou Projetista com id: " + projetistaCode + " ja existem");
        }
        try {
            Projeto projeto = em.find(Projeto.class,id);
            if (projeto == null) {
                throw new MyEntityExistsException("O projeto ja foi criado");
            }
            projeto = new Projeto(id, nome, cliente, projetista);
            em.persist(projeto);
            cliente.addProjetos(projeto);
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

    public void enrollsProjetoInClienteandProjetista(int id, int clienteCode, int projetistaCode){
        Projeto projeto = em.find(Projeto.class,id);
        if(projeto == null){
            throw new IllegalArgumentException();
        }
        Cliente cliente = em.find(Cliente.class,clienteCode);
        if(cliente == null){
            throw new IllegalArgumentException();
        }
        Projetista projetista = em.find(Projetista.class,projetistaCode);
        if(projetista == null){
            throw new IllegalArgumentException();
        }

        if(projeto.getProjetista().equals(projetista) && !cliente.getProjetos().contains(projeto) && !projetista.getProjetos().contains(projeto)){
            cliente.addProjetos(projeto);
            projetista.addProjetos(projeto);
        }

    }

}

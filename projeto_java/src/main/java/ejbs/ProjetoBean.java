package ejbs;

import entities.Cliente;
import entities.Projetista;
import entities.Projeto;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjetoBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome, int clienteCode, int projetistaCode) throws MyEntityExistsException {
        Cliente cliente = em.find(Cliente.class, clienteCode);
        Projetista projetista = em.find(Projetista.class, projetistaCode );
        if(cliente != null && projetista != null) {

            Projeto projeto = new Projeto(id, nome, cliente, projetista);
            if(projeto == null){
                throw new MyEntityExistsException("O projeto ja foi criado");
            }
            em.persist(projeto);
            cliente.addProjetos(projeto);
        }
    }

    public List<Projeto> getAllProjetos() {
        try {
            return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
        } catch(Exception exception){
            throw new EJBException("ERROR_RETRIEVING_PROJETOS",exception);
        }
    }

    public void enrollsProjetoInCliente(int id, int clienteCode, int projetistaCode){
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

package ejbs;

import entities.Cliente;
import entities.Projetista;
import entities.Projeto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjetoBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome, int clienteCode, int projetistaCode){
        Cliente cliente = em.find(Cliente.class, clienteCode);
        Projetista projetista = em.find(Projetista.class, projetistaCode );
        if(cliente != null && projetista != null) {
            Projeto projeto = new Projeto(id, nome, cliente, projetista);
            em.persist(projeto);//permite crear un estudiante en la base de datos
            cliente.addProjetos(projeto);
        }
    }

    public List<Projeto> getAllProjetos() {
// remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
    }

}

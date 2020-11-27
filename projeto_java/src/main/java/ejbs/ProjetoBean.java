package ejbs;

import entities.Cliente;
import entities.Projeto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjetoBean {
    @PersistenceContext
    EntityManager em;

    public void create(int id, String nome, int clienteCode){
        Cliente cliente = em.find(Cliente.class, clienteCode);
        if(cliente != null) {
            Projeto projeto = new Projeto(id, nome, cliente);
            em.persist(projeto);//permite crear un estudiante en la base de datos
            cliente.addProjetos(projeto);
        }
    }

    public List<Projeto> getAllProjetos() {
// remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
    }

}

package ejbs;

import entities.Cliente;
import entities.Documento;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "DocumentoEJB")
public class DocumentoBean {
    @PersistenceContext
    EntityManager em;

    public void create(String username, String filepath, String fileName)
            throws MyEntityNotFoundException {
        try {
            Cliente cliente = em.find(Cliente.class, username);
            if(cliente == null){
                throw new MyEntityNotFoundException("Student with username: " + username + " not found");
            }
            Documento documento = new Documento(filepath, fileName, cliente);
            em.persist(documento);
            cliente.addDocument(documento);
        } catch (MyEntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException("ERROR_CREATING_DOCUMENT ----> ", e);
        }
    }

    public Documento findDocumento(int id) {
        try{
            return em.find(Documento.class, id);
        } catch (Exception e) {
            throw new EJBException("ERROR_FINDING_DOCUMENT ----> ", e);
        }
    }

    public List<Documento> getClienteDocumentos(String username) {
        try{
            return em.createNamedQuery("getClientesDocumentos", Documento.class).
                    setParameter("username", username).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_FINDING_DOCUMENT ----> ", e);
        }
    }

}

package ejbs;

import entities.Estructura;
import entities.Projetista;
import entities.Projeto;
import exceptions.MyEntityExistsException;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EstructuraBean {
    @PersistenceContext
    EntityManager entityManager;

    public void Create(String nome, String tipoMaterial, int nb, double LVao, int q, int projetoCode) throws MyEntityExistsException {
        Projeto projeto = entityManager.find(Projeto.class,projetoCode);
        Estructura estructura = new Estructura(nome,tipoMaterial,nb,LVao,q, projeto);
        if(estructura == null){
            throw new MyEntityExistsException("A estrutura ja foi criada");
        }
        entityManager.persist(estructura);

    }
    public List<Estructura> getAllEstructuras() {
        try {
            return entityManager.createNamedQuery("getAllEstrucutras", Estructura.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_ESTRUCTURAS", e);
        }

    }

}

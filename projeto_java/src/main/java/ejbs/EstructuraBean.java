package ejbs;

import entities.Estructura;
import entities.Projetista;
import entities.Projeto;
import entities.TipoEstructura;
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
public class EstructuraBean {
    @PersistenceContext
    EntityManager entityManager;


    public void create(String nome, TipoEstructura tipoMaterial, int nb, double LVao, int q, int projetoCode) throws MyEntityExistsException, MyEntityNotFoundException,MyConstraintViolationException {
        try{
            Estructura estructura = entityManager.find(Estructura.class,nome);
            //System.out.println("ESTRUCTURA"+estructura.toString());
            Projeto projeto = entityManager.find(Projeto.class,projetoCode);
            //System.out.println("ESTRUCTURA"+estructura.toString());
            estructura = new Estructura(nome,tipoMaterial,nb,LVao,q,projeto);
            entityManager.persist(estructura);
            projeto.addEstructuras(estructura);
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public Estructura findEstrutura(String nome){
        return entityManager.find(Estructura.class, nome);
    }

    public void updateEstrutura(String nome, TipoEstructura tipoMaterial, int nb, double LVao, int q, int projetoCode) throws MyEntityNotFoundException{
        Estructura estructura =  findEstrutura(nome);
        if(estructura == null) {
            throw new MyEntityNotFoundException("Estrutura com nome " + nome + " nao encontrada.");
        }
        Projeto projeto = entityManager.find(Projeto.class, projetoCode);
        if(projeto == null) {
            throw new MyEntityNotFoundException("Project with code " + projeto + " not found.");
        }
        entityManager.lock(estructura, LockModeType.OPTIMISTIC);
        estructura.setNome(nome);
        estructura.setTipoMaterial(tipoMaterial);
        estructura.setNb(nb);
        estructura.setLVao(LVao);
        estructura.setQ(q);
        estructura.setProjeto(projeto); ///////Implementar unroll

        entityManager.merge(estructura);

    }

    public void deleteEstrutura(String nome)throws MyEntityNotFoundException{

        Estructura estructura = findEstrutura(nome);
        System.out.printf("Estrutura a remover "+ estructura);
        if(estructura == null) {
            throw new MyEntityNotFoundException("Estrutura com nome " + nome + " nao encontrada.");
        }
        entityManager.remove(estructura);

    }

    public List<Estructura> getAllEstructuras() {
        try {
            return entityManager.createNamedQuery("getAllEstructuras", Estructura.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_ESTRUCTURAS", e);
        }

    }

}

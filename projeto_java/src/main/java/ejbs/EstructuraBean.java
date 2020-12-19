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

        entityManager.merge(estructura);

    }

    public void deleteEstrutura(String nome)throws MyEntityNotFoundException{

        Estructura estructura = findEstrutura(nome);
        System.out.printf("Estrutura a remover "+ estructura);
        if(estructura == null) {
            throw new MyEntityNotFoundException("Estrutura com nome " + nome + " nao encontrada.");
        }
        Projeto projeto = entityManager.find(Projeto.class, estructura.getProjeto().getId());

        projeto.removeEstrucutras(estructura);
        unrollEstruturaProjeto(projeto.getId(),estructura.getNome());
        entityManager.remove(estructura);


    }

    public List<Estructura> getAllEstructuras() {
        try {
            return entityManager.createNamedQuery("getAllEstructuras", Estructura.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_ESTRUCTURAS", e);
        }

    }

    public void enrollEstructuraFromVariante(String nome, int varianteCode) throws MyEntityNotFoundException{
        Estructura estructura = entityManager.find(Estructura.class, nome);
        if (estructura == null) {
            throw new MyEntityNotFoundException("Student with username " + nome + " not found.");
        }
        Variante variante = entityManager.find(Variante.class, varianteCode);
        if (variante == null) {
            throw new MyEntityNotFoundException("Subject with code " + varianteCode + " not found.");
        }

        if(!estructura.getVariantes().contains(variante)) {
            estructura.addVariante(variante);
            variante.addEstructura(estructura);
        }
    }

    public void unrollEstructuraFromVariante(String nome, int varianteCode) throws MyEntityNotFoundException{
        Estructura estructura = entityManager.find(Estructura.class, nome);
        if (estructura == null) {
            throw new MyEntityNotFoundException("Student with username " + nome + " not found.");
        }
        Variante variante = entityManager.find(Variante.class, varianteCode);
        if (variante == null) {
            throw new MyEntityNotFoundException("Subject with code " + varianteCode + " not found.");
        }

        if(estructura.getVariantes().contains(variante)) {
            estructura.removeVariante(variante);
            variante.removeEstructura(estructura);
        }
    }

    public void enrollsEstruturaInProjeto(int id, String estruturaCode) throws MyEntityNotFoundException {
        Projeto projeto = entityManager.find(Projeto.class,id);
        if(projeto == null){
            throw new MyEntityNotFoundException("Material com nome " + id + " nao encontrada.");
        }
        Estructura estructura = entityManager.find(Estructura.class,estruturaCode);

        if(estructura == null){
            throw new MyEntityNotFoundException("Material com nome " + estruturaCode + " nao encontrada.");
        }
        projeto.addEstructuras(estructura);
        estructura.setProjeto(projeto);
    }

    public void unrollEstruturaProjeto(int id, String estruturaCode) throws MyEntityNotFoundException {
        Projeto projeto = entityManager.find(Projeto.class,id);
        if(projeto == null){
            throw new MyEntityNotFoundException("Material com nome " + id + " nao encontrada.");
        }
        Estructura estructura = entityManager.find(Estructura.class,estruturaCode);

        if(estructura == null){
            throw new MyEntityNotFoundException("Material com nome " + estruturaCode + " nao encontrada.");
        }
        projeto.removeEstrucutras(estructura);
        estructura.setProjeto(null);
    }

}

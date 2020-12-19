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
public class MaterialBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(String name, String fabricante) throws MyEntityExistsException, MyConstraintViolationException {
        try {
        Material material = entityManager.find(Material.class,name);
        Fabricante fabricanteFind = entityManager.find(Fabricante.class,fabricante);
            material = new Material(name, fabricanteFind);
            entityManager.persist(material);
            fabricanteFind.addMateriales(material);
        }catch(ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public Material findMaterial(String name){
        return entityManager.find(Material.class, name);
    }

    public List<Material> getAllMateriales() {
        try {
            return entityManager.createNamedQuery("getAllMateriales", Material.class).getResultList();
        } catch (Exception e) {
            throw new EJBException("ERROR_RETRIEVING_MATERIALES", e);
        }
    }

    public void updateMaterial(String name, String fabricanteCode) throws MyEntityNotFoundException {
        Material material =  findMaterial(name);
        if(material == null) {
            throw new MyEntityNotFoundException("Material com nome " + name + " nao encontrada.");
        }
        Fabricante fabricante = entityManager.find(Fabricante.class, fabricanteCode);

        if(fabricante == null) {
            throw new MyEntityNotFoundException("Fabricante with code " + fabricanteCode + " not found.");
        }
        entityManager.lock(material, LockModeType.OPTIMISTIC);
        material.setNome(name);
        material.setFabricante(fabricante);

        entityManager.merge(material);
    }
    public void deleteMaterial(String name)throws MyEntityNotFoundException{

        Material material = findMaterial(name);
        System.out.printf("Material a remover "+ material);
        if(material == null) {
            throw new MyEntityNotFoundException("Material com nome " + name + " nao encontrada.");
        }
        Fabricante fabricante = entityManager.find(Fabricante.class, material.getFabricante().getUsername());
        fabricante.removeMaterial(material);
        unrollMaterialFabricante(material.getNome(),fabricante.getUsername());
        entityManager.remove(material);


    }

    public void enrollsMaterialFabricante(String nome, String fabricanteCode){
        Material material = entityManager.find(Material.class,nome);
        if(material == null){
            throw new IllegalArgumentException();
        }
        Fabricante fabricante = entityManager.find(Fabricante.class,fabricanteCode);
        if(fabricante == null){
            throw new IllegalArgumentException();
        }
        fabricante.addMateriales(material);
        material.setFabricante(fabricante);
    }

    public void unrollMaterialFabricante(String nome, String fabricanteCode){
        Material material = entityManager.find(Material.class,nome);
        if(material == null){
            throw new IllegalArgumentException();
        }
        Fabricante fabricante = entityManager.find(Fabricante.class,fabricanteCode);
        if(fabricante == null){
            throw new IllegalArgumentException();
        }
        fabricante.removeMaterial(material);
        material.setFabricante(null);
    }
}

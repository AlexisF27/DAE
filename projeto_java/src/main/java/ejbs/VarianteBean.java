package ejbs;

import entities.Material;
import entities.Variante;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VarianteBean {

    @PersistenceContext
    EntityManager entityManager;

    public void create(int codigo, String nomeProduto, String name, double weff_p, double weff_n, double ar, double sigmaC){
        Material material = entityManager.find(Material.class, nomeProduto);
        Variante variante = new Variante(codigo,material,name,weff_p,weff_n,ar,sigmaC);
        entityManager.persist(variante);
    }
}

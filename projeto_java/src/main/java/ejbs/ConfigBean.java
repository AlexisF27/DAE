package ejbs;

import entities.Fabricante;
import entities.PessoaContacto;
import entities.Variante;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton(name = "ConfigEJB")
@Startup
public class ConfigBean {
    @EJB
    ClienteBean clienteBean;
    @EJB
    ProjetoBean projetoBean;
    @EJB
    ProjetistaBean projetistaBean;
    @EJB
    EstructuraBean estructuraBean;
    @EJB
    MaterialBean materialBean;
    @EJB
    VarianteBean varianteBean;
    @EJB
    FabricanteBean fabricanteBean;
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");


    @PostConstruct
    public void populateDB(){
        try {
            System.out.println("Hello Java EE!");


            System.out.println("++++++++++++++\n"+"CREATING CLIENT .......\n"+"+++++++++++++");
            clienteBean.create("cliente1", "Alexis", "Leiria", "jalskfdja","alexis@mail.pt", new PessoaContacto("Alexis", "alexis@mail.pt", 999999999));

            System.out.println("++++++++++++++\n"+"CREATING PROJETISTA .......\n"+"+++++++++++++");
            projetistaBean.create("projetista1", "kjalksdjfs","Hola", "hola@hola.pt");

            System.out.println("++++++++++++++\n"+"CRATING FABRICANTE .......\n"+"+++++++++++++");
            fabricanteBean.create("fabricante1","Toto","kjaslfkjas","toto@mail.pt");

            System.out.println("++++++++++++++\n"+"CRATING PROJECT .......\n"+"+++++++++++++");
            projetoBean.create(2, "PrimerProjeto",  "projetista1");

            System.out.println("++++++++++++++\n"+"ENROLLING PROJECT - PROJETISTA .......\n"+"+++++++++++++");
            projetoBean.enrollsProjetoInProjetista(2,"projetista1");

            System.out.println("++++++++++++++\n"+"CRATING MATERIALES .......\n"+"+++++++++++++");
            //Materiales
            materialBean.create("PrimerMaterial");
            //Variantes
            System.out.println("++++++++++++++\n"+"CRATING VARIANTES .......\n"+"+++++++++++++");
             varianteBean.create(1,"PrimerMaterial","PrimerProducto",6.7,6.7,6.7,6.7);
            //Estructuras
            System.out.println("++++++++++++++\n"+"CRATING ESTRUCTURA .......\n"+"+++++++++++++");
        //    estructuraBean.create("PrimerEstrutura","PrimerMaterial",1,11,2,1);
        }catch (Exception exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }

    }
}

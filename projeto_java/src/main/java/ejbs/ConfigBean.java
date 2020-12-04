package ejbs;

import entities.PessoaContacto;

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
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @PostConstruct
    public void populateDB(){
        try {
            System.out.println("Hello Java EE!");

            //Clientes
            clienteBean.create(1, "Alexis", "Leiria", "alexis@mail.pt", new PessoaContacto("Alexis", "alexis@mail.pt", 999999999));
            //Projetos
            projetoBean.create(1, "PrimerProjeto", 1, 1);
            //Projetista
            projetistaBean.create(1, "Hola", "hola@hola.pt");
        }catch (Exception exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }

    }
}

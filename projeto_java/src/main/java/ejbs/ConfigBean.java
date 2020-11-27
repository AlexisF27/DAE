package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton(name = "ConfigEJB")
@Startup
public class ConfigBean {
    @EJB
    ClienteBean clienteBean;
    @EJB
    ProjetoBean projetoBean;

    @PostConstruct
    public void populateDB(){
        System.out.println("Hello Java EE!");

        //Clientes
        clienteBean.create(1,"Alexis","Leiria", "alexis@mail.pt","110000000");
        //Projetos
        projetoBean.create(1,"PrimerProjeto",1);

    }
}

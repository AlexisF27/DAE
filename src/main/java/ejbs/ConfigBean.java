package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton(name = "ConfigEJB")
@Startup
public class ConfigBean {

    @PostConstruct
    public void populateDB(){
        System.out.println("Hello Java EE!"); }
}

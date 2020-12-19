package ejbs;

import entities.Fabricante;
import entities.PessoaContacto;
import entities.TipoEstructura;
import entities.Variante;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.LinkedHashMap;
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
    @EJB
    SimulacaoBean simulacaoBean;
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");


    @PostConstruct
    public void populateDB(){
        try {
            System.out.println("Hello Java EE!");


            System.out.println("++++++++++++++\n"+"CREATING CLIENT .......\n"+"+++++++++++++");
            clienteBean.create("cliente1", "Alexis", "Leiria", "123","alexis@mail.pt", new PessoaContacto("Alexis", "alexis@mail.pt", 999999999));

            System.out.println("++++++++++++++\n"+"CREATING PROJETISTA .......\n"+"+++++++++++++");
            projetistaBean.create("projetista1", "Hola","123", "hola@hola.pt");
            projetistaBean.create("projetista2", "Chao", "123", "pl@mail.pt");

            System.out.println("++++++++++++++\n"+"CRATING FABRICANTE .......\n"+"+++++++++++++");
            fabricanteBean.create("fabricante1","Toto","123","toto@mail.pt");
            fabricanteBean.create("fabricante2", "asdasd","123","f@mail.pt");

            System.out.println("++++++++++++++\n"+"CRATING PROJECT .......\n"+"+++++++++++++");
            projetoBean.create(2, "PrimerProjeto",  "projetista1");
            projetoBean.create(3, "SegundoProjeto",  "projetista1");
            projetoBean.create(4, "CuartoProjeto", "projetista1");



            System.out.println("++++++++++++++\n"+"CRATING MATERIALES .......\n"+"+++++++++++++");
            //Materiales
            materialBean.create("Section Z 220 BF","fabricante1");
            materialBean.create("Section C 220 BF","fabricante1");
            materialBean.create("Section F 220 BF", "fabricante2");
            materialBean.create("Material2", "fabricante2");
            //Variantes
            System.out.println("++++++++++++++\n"+"CRATING VARIANTES .......\n"+"+++++++++++++");
            varianteBean.create(1, "Section C 220 BF", "C 120/50/21 x 1.5", 13846, 13846, 375, 220000);
            varianteBean.create(2, "Section C 220 BF", "C 120/60/13 x 2.0", 18738, 18738, 500, 220000);
            varianteBean.create(3, "Material2", "C 120/60/13 x 2.0", 18738, 18738, 500, 220000);
            System.out.println("++++++++++++++\n"+"Prueba .......\n"+"+++++++++++++");
            Variante variante1 = varianteBean.getVariante(1);

            System.out.println("+++++++++++++++++++++++++++");

            variante1.addMcr_p(3.0,243.2123113);
            variante1.addMcr_p(4.0,145.238784);
            variante1.addMcr_p(5.0,99.15039028);
            variante1.addMcr_p(6.0,73.71351699);
            variante1.addMcr_p(7.0,58.07716688);
            variante1.addMcr_p(8.0,47.68885195);
            variante1.addMcr_p(9.0,40.37070843);
            variante1.addMcr_p(10.0,34.9747033);
            variante1.addMcr_p(11.0,30.84866055);
            variante1.addMcr_p(12.0,27.59984422);
            System.out.println("+++++++++++++++++++++++++++");
            //Válido para variantes simétricas, em que os mcr_p são iguais aos mcr_n
            variante1.setMcr_n((LinkedHashMap<Double, Double>) variante1.getMcr_p().clone());

            System.out.println("+++++++++++++++++++++++++++");
            Variante variante2 = varianteBean.getVariante(2);
            variante2.addMcr_p(3.0,393.1408237);
            variante2.addMcr_p(4.0,241.9157907);
            variante2.addMcr_p(5.0,169.7815504);
            variante2.addMcr_p(6.0,129.3561949);
            variante2.addMcr_p(7.0,104.0782202);
            variante2.addMcr_p(8.0,86.9803928);
            variante2.addMcr_p(9.0,74.71876195);
            variante2.addMcr_p(10.0,65.52224563);
            variante2.addMcr_p(11.0,58.37786338);
            variante2.addMcr_p(12.0,52.65428332);

            //Válido para variantes de geometria simétrica, em que os mcr_p são iguais aos mcr_n
            variante2.setMcr_n((LinkedHashMap<Double, Double>) variante2.getMcr_p().clone());

            //EXEMPLO DA SIMULAÇÃO PARA DUAS VARIANTES DO PERFIL C, E PARA UMA ESTRUTURA DE 3 vãos (nb) de 3m cada (LVao) E SOBRECARGA 500000 (q)
            if(simulacaoBean.simulaVariante(3, 3.0, 500000, variante1)){
                System.out.println(variante1.getNome() + " pode ser usada.");
            }else{
                System.out.println(variante1.getNome() + " não pode ser usada.");
            }

            if(simulacaoBean.simulaVariante(3, 3.0, 500000, variante2)){
                System.out.println("A variante " + variante2.getNome() + " pode ser usada.");
            }else{
                System.out.println("A variante " + variante2.getNome() + " não pode ser usada.");
            }

            //Estructuras
            System.out.println("++++++++++++++\n"+"CRATING ESTRUCTURA .......\n"+"+++++++++++++");
            System.out.println("++++++++++++++\n"+TipoEstructura.Chapa+"+++++++++++++");
            estructuraBean.create("PrimerEstrutura", TipoEstructura.Laje, 1,11,2,2);
            estructuraBean.create("SegundaEstrutura", TipoEstructura.Chapa, 10,11,20,3);
            estructuraBean.deleteEstrutura("PrimerEstrutura");

            //estructuraBean.create("PrimerEstrutura", TipoEstructura.Laje,1,11,2,2);
            //estructuraBean.create("PrimerEstrutura", TipoEstructura.Painel,1,11,2,2);
            //estructuraBean.create("PrimerEstrutura", TipoEstructura.Perfis,1,11,2,2);

            System.out.println("++++++++++++++\n"+"ENROLLING ESTRUTURA - VARIANTE .......\n"+"+++++++++++++");
            estructuraBean.enrollEstructuraFromVariante("SegundaEstrutura",1);
            estructuraBean.enrollEstructuraFromVariante("SegundaEstrutura",2);
            estructuraBean.unrollEstructuraFromVariante("SegundaEstrutura",1);


            System.out.println("++++++++++++++\n"+"ENROLLING ESTRUTURA - PROJETO .......\n"+"+++++++++++++");
      //      estructuraBean.enrollsEstruturaInProjeto(2,"PrimerEstrutura");


        }catch (Exception exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }

    }
}

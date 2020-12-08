package ws;

import dtos.ProjetoDTO;
import ejbs.ProjetoBean;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/projetos") // relative url web path for this service
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class ProjetoServices {
    @EJB
    private ProjetoBean projetoBean;

    private ProjetoDTO toDTO(Projeto projeto){
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                projeto.getCliente().getId(),
                projeto.getProjetista().getId()
        );
    }

}

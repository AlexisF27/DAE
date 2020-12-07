package ws;

import dtos.ClienteDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.ProjetistaBean;
import entities.Cliente;
import entities.Projetista;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/projetistas")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class ProjetistaServices {
    @EJB
    ProjetistaBean projetistaBean;
    private ProjetistaDTO toDTO(Projetista projetista) {

        return new ProjetistaDTO(
                projetista.getId(),
                projetista.getName(),
                projetista.getMail()
        );

    }

    private List<ProjetistaDTO> toDTOs(List<Projetista> projetistas){
        return projetistas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    // means: to call this endpoint, we need to use the HTTP GET method @Path("/") // means: the relative url path is “/api/students/”
    public List<ProjetistaDTO> getAllProjetistas() {
        return toDTOs(projetistaBean.getAllProjetistas());
    }


}

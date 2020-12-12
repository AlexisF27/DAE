package ws;

import dtos.ProjetistaDTO;
import ejbs.ProjetistaBean;
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
                projetista.getUsername(),
                projetista.getNome(),
                projetista.getEmail()
        );

    }

    private List<ProjetistaDTO> toDTOs(List<Projetista> projetistas){
        return projetistas.stream().map(this::toDTO).collect(Collectors.toList());
    }




}

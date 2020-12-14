package ws;


import dtos.EstructuraDTO;
import dtos.ProjetistaDTO;
import ejbs.EstructuraBean;
import ejbs.ProjetistaBean;
import entities.Estructura;
import entities.Projetista;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/estruturas")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class EstruturaServices {

    @EJB
    EstructuraBean estructuraBean;
    private EstructuraDTO toDTO(Estructura estructura) {

        return new EstructuraDTO(
                estructura.getNome(),
                estructura.getTipoMaterial(),
                estructura.getNb(),
                estructura.getLVao(),
                estructura.getQ(),
                estructura.getProjeto().getId()
        );

    }

    private List<EstructuraDTO> toDTOs(List<Estructura> estructuras) {
        return estructuras.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET // means: to cagetallEstructurasll this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/students/”
    public List<EstructuraDTO> getAllEstruturasWS() {
        return toDTOs(estructuraBean.getAllEstructuras());
    }

    @POST
    @Path("/")
    public Response createNewEstrutura(EstructuraDTO estructuraDTO) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        estructuraBean.create(
                estructuraDTO.getNome(),
                estructuraDTO.getTipoMaterial(),
                estructuraDTO.getNb(),
                estructuraDTO.getLVAo(),
                estructuraDTO.getQ(),
                estructuraDTO.getProjetoCode()
        );
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{nome}")
    public Response updateNewEstrutura(EstructuraDTO estructuraDTO) throws MyEntityNotFoundException {

        estructuraBean.updateEstrutura(estructuraDTO.getNome(),
                estructuraDTO.getTipoMaterial(),
                estructuraDTO.getNb(),
                estructuraDTO.getLVAo(),
                estructuraDTO.getQ(),
                estructuraDTO.getProjetoCode()
                );

        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{nome}")
    public Response removeNewEstrutura(@PathParam("nome") String nome) throws MyEntityNotFoundException {

        estructuraBean.deleteEstrutura(nome);

        return Response.status(Response.Status.CREATED).build();
    }


}

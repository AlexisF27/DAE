package ws;

import dtos.ProjetoDTO;
import ejbs.ProjetoBean;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


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

    private List<ProjetoDTO> toDTOs(List<Projeto> projetos){
        return projetos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<ProjetoDTO> getAllProjetosWS(){
        return toDTOs(projetoBean.getAllProjetos());
    }

    @POST
    @Path("/")
    public Response createNewProjeto(ProjetoDTO projetoDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        projetoBean.create(
                projetoDTO.getId(),
                projetoDTO.getNome(),
                projetoDTO.getClienteCode(),
                projetoDTO.getProjetistaCode()
        );
        return Response.status(Response.Status.CREATED).build();
    }

}

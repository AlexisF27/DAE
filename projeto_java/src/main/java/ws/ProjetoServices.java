package ws;

import dtos.EstructuraDTO;
import dtos.ProjetoDTO;
import ejbs.ProjetoBean;
import entities.Estructura;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@Path("/projetos") // relative url web path for this service
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class ProjetoServices {
    @EJB
    private ProjetoBean projetoBean;


    ProjetoDTO toDTONaEstrutura(Projeto projeto){
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                null,
                projeto.getProjetista().getUsername()
        );
    }

    List<ProjetoDTO> toDTOsNaEstruturas(List<Projeto> projetos){
        return projetos.stream().map(this::toDTONaEstrutura).collect(Collectors.toList());
    }

    private ProjetoDTO toDTO(Projeto projeto){
        ProjetoDTO projetoDTO =  new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                null,
                projeto.getProjetista().getUsername()
        );

        List<EstructuraDTO> estructuraDTOS = new LinkedList<>();
        for(Estructura estructura: projeto.getEstructuras()){
            estructuraDTOS.add(toDTO(estructura));
        }
            //    estruturasToDTOs(projeto.getEstructuras());
            //    projetoDTO.setEstructuras(estructuraDTOS);

        return projetoDTO;
    }

    List<EstructuraDTO> estruturasToDTOs(List<Estructura> estruturas){
        return estruturas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    EstructuraDTO toDTO(Estructura estructura){
        return new EstructuraDTO(
                estructura.getNome(),
                estructura.getTipoMaterial(),
                estructura.getNb(),
                estructura.getLVao(),
                estructura.getQ(),
                estructura.getProjeto().getId()
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
                projetoDTO.getProjetistaCode()
        );
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/enrollCliente")
    public Response enrollClienteProjeto(ProjetoDTO projetoDTO){



        projetoBean.enrollsProjetoInCliente(projetoDTO.getId(),projetoDTO.getClienteCode());

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProjeto(ProjetoDTO projetoDTO) throws MyEntityNotFoundException {

        projetoBean.updateProjeto(projetoDTO.getId(),
                projetoDTO.getNome(),
                projetoDTO.getProjetistaCode()
        );

        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeProjeto(@PathParam("id") int id) throws MyEntityNotFoundException {

        projetoBean.deleteProjeto(id);

        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    @Path("{id}/estruturas")
    public Response getProjetosEstruturas(@PathParam("id") int id) throws MyEntityNotFoundException{
        Projeto projeto = projetoBean.findProjeto(id);
        if(projeto == null){
            throw new MyEntityNotFoundException("Projeto with id " + id + " not found");
        }

        return Response.status(Response.Status.OK)
                .entity(estruturasToDTOs(projeto.getEstructuras()))
                .build();
    }

}

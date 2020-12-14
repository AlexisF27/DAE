package ws;


import dtos.EstructuraDTO;
import dtos.MaterialDTO;
import ejbs.MaterialBean;
import entities.Estructura;
import entities.Material;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/materiales")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class MaterialServices {

    @EJB
    MaterialBean materialBean;

    private MaterialDTO toDTO(Material material) {

        return new MaterialDTO(
                material.getNome(),
                material.getFabricante().getUsername()
        );
    }

    private List<MaterialDTO> toDTOs(List<Material> materiales) {
        return materiales.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET // means: to cagetallEstructurasll this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/students/”
    public List<MaterialDTO> getAllMaterialesWS() {
        return toDTOs(materialBean.getAllMateriales());
    }

    @POST
    @Path("/")
    public Response createNewMaterial(MaterialDTO materialDTO) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        materialBean.create(
                materialDTO.getNome(),
                materialDTO.getFabricante()
        );
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{nome}")
    public Response updateMaterial(MaterialDTO materialDTO) throws MyEntityNotFoundException {

        materialBean.updateMaterial(materialDTO.getNome(),
                materialDTO.getFabricante()
        );

        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{nome}")
    public Response removeNewEstrutura(@PathParam("nome") String nome) throws MyEntityNotFoundException {
        System.out.printf("uuuuuuuu");
        materialBean.deleteMaterial(nome);

        return Response.status(Response.Status.CREATED).build();
    }
}

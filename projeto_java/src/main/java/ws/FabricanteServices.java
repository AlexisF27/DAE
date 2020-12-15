package ws;



import dtos.FabricanteDTO;
import dtos.MaterialDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.FabricanteBean;
import entities.Fabricante;
import entities.Material;
import entities.Projetista;
import entities.Projeto;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/fabricantes")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})

public class FabricanteServices {

    @EJB
    FabricanteBean fabricanteBean;

    private FabricanteDTO toDTO(Fabricante fabricante) {

        FabricanteDTO fabricanteDTO =  new FabricanteDTO(
                fabricante.getUsername(),
                fabricante.getNome(),
                fabricante.getEmail()
        );

        List<MaterialDTO> materialDTOSs = new LinkedList<>();
        for(Material material: fabricante.getMateriales()){
            materialDTOSs.add(toDTO(material));
        }

        return fabricanteDTO;
    }

    MaterialDTO toDTO(Material material){
        return new MaterialDTO(
                material.getNome(),
                material.getFabricante().getUsername()
        );
    }

    FabricanteDTO toDTONoMaterial(Fabricante fabricante){
        return new FabricanteDTO(
                fabricante.getUsername(),
                fabricante.getNome(),
                fabricante.getPassword()
        );
    }

    private List<FabricanteDTO> toDTOs(List<Fabricante> fabricantes){
        return fabricantes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    List<MaterialDTO> materialDTOSs(List<Material> materials){
        return materials.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("{username}/materiales")
    public Response getFabricantesMateriales(@PathParam("username") String username) throws MyEntityNotFoundException {
        Fabricante fabricante = fabricanteBean.findFabricante(username);

        if(fabricante == null){
            throw new MyEntityNotFoundException("Fabricante with id " + username + " not found");
        }

        return Response.status(Response.Status.OK)
                .entity(materialDTOSs(fabricante.getMateriales()))
                .build();
    }

}

package ws;


import dtos.ClienteDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.ClienteBean;
import entities.Cliente;
import entities.PessoaContacto;
import entities.Projetista;
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
import java.util.stream.Collectors;

@RolesAllowed({"Cliente"})
@Path("/clientes")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”

public class ClienteServices {
    @EJB
    private ClienteBean clienteBean;

    private ClienteDTO toDTO(Cliente cliente) {

        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getUsername(),
                cliente.getNome(),
                cliente.getPassword(),
                cliente.getMorada(),
                cliente.getEmail(),
                new PessoaContacto(cliente.getPessoaContacto().getNome(),cliente.getPessoaContacto().getEmail(),cliente.getPessoaContacto().getTelefone())
        );
        List<ProjetoDTO> projetoDTOSs = new LinkedList<>();
        for(Projeto projeto: cliente.getProjetos()){
            projetoDTOSs.add(toDTO(projeto));
        }
        return clienteDTO;
    }

    ProjetoDTO toDTO(Projeto projeto){
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                null,
                projeto.getProjetista().getUsername()
        );
    }

    ClienteDTO toDTONoProjeto(Cliente cliente){
        return new ClienteDTO(
                cliente.getUsername(),
                cliente.getNome(),
                cliente.getPassword(),
                cliente.getMorada(),
                cliente.getEmail(),
                new PessoaContacto(cliente.getPessoaContacto().getNome(),cliente.getPessoaContacto().getEmail(),cliente.getPessoaContacto().getTelefone())
        );
    }

    private List<ClienteDTO> toDTOs(List<Cliente> clientes){
        return clientes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    List<ProjetoDTO> projetoDTOSs(List<Projeto> projetos){
        return projetos.stream().map(this::toDTO).collect(Collectors.toList());
    }


    @GET
    @Path("{username}/projetos")
    public Response getClientesProjetos(@PathParam("username") String username) throws MyEntityNotFoundException {
       Cliente cliente = clienteBean.findCliente(username);

        if(cliente == null){
            throw new MyEntityNotFoundException("Cliente with id " + username + " not found");
        }

        return Response.status(Response.Status.OK)
                .entity(projetoDTOSs(cliente.getProjetos()))
                .build();
    }

}

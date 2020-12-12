package ws;


import dtos.ClienteDTO;
import ejbs.ClienteBean;
import entities.Cliente;
import entities.PessoaContacto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/clientes")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”

public class ClienteServices {
    @EJB
    private ClienteBean clienteBean;

    private ClienteDTO toDTO(Cliente cliente) {

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



    @POST
    @Path("/")
    public Response createNewCliente(ClienteDTO clienteDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        clienteBean.create(
                clienteDTO.getUsername(),
                clienteDTO.getNome(),
                clienteDTO.getPassword(),
                clienteDTO.getMorada(),
                clienteDTO.getMail(),
                new PessoaContacto(clienteDTO.getPessoaContacto().getNome(),clienteDTO.getPessoaContacto().getEmail(),clienteDTO.getPessoaContacto().getTelefone())
        );

        return Response.status(Response.Status.CREATED).build();
    }

}

package ws;


import dtos.ClienteDTO;
import entities.Cliente;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/cliente")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”

public class ClienteServices {
    @EJB
    private ClienteServices clienteBean;

    private ClienteDTO toDTO(Cliente cliente) {

        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getMorada(),
                cliente.getMail(),
                cliente.getPessoaContacto()
        );
        return clienteDTO;
    }

    private List<ClienteDTO> toDTOs(List<Cliente> clientes){
        return clientes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    // means: to call this endpoint, we need to use the HTTP GET method @Path("/") // means: the relative url path is “/api/students/”
    public List<ClienteDTO> getAllClientesWS() {
        return toDTOs(clienteBean.getAllClientes()); }
}

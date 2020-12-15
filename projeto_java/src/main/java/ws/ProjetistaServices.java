package ws;

import dtos.EmailDTO;
import dtos.EstructuraDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.ClienteBean;
import ejbs.EmailBean;
import ejbs.ProjetistaBean;
import entities.Cliente;
import entities.Estructura;
import entities.Projetista;
import entities.Projeto;
import exceptions.MyEntityNotFoundException;


import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/projetistas")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class ProjetistaServices {
    @EJB
    ProjetistaBean projetistaBean;

    @EJB
    ClienteBean clienteBean;

    @EJB
    EmailBean emailBean;

    private ProjetistaDTO toDTO(Projetista projetista) {

        ProjetistaDTO projetistaDTO =  new ProjetistaDTO(
                projetista.getUsername(),
                projetista.getNome(),
                projetista.getEmail()
        );

        List<ProjetoDTO> projetoDTOSs = new LinkedList<>();
        for(Projeto projeto: projetista.getProjetos()){
            projetoDTOSs.add(toDTO(projeto));
        }

        return projetistaDTO;

    }

    ProjetoDTO toDTO(Projeto projeto){
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                null,
                projeto.getProjetista().getUsername()
        );
    }

    ProjetistaDTO toDTONoProjeto(Projetista projetista){
        return new ProjetistaDTO(
                projetista.getUsername(),
                projetista.getNome(),
                projetista.getEmail()
        );
    }

    private List<ProjetistaDTO> toDTOs(List<Projetista> projetistas){
        return projetistas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    List<ProjetoDTO> projetoDTOSs(List<Projeto> projetos){
        return projetos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("{username}/projetos")
    public Response getProjetistasProjetos(@PathParam("username") String username) throws MyEntityNotFoundException {
        Projetista projetista = projetistaBean.findProjetista(username);

        if(projetista == null){
            throw new MyEntityNotFoundException("Projetista with id " + username + " not found");
        }

        return Response.status(Response.Status.OK)
                .entity(projetoDTOSs(projetista.getProjetos()))
                .build();
    }

    @POST
    @Path("/{username}/email")
    public Response sendEmail(EmailDTO email, String clienteCode)
            throws MyEntityNotFoundException, MessagingException {

        Cliente cliente = clienteBean.findCliente(clienteCode);
        if (cliente == null) {
            throw new MyEntityNotFoundException("Student with username '" + clienteCode
                    + "' not found in our records.");
        }
        emailBean.send(cliente.getEmail(), email.getSubject(), email.getMessage());
        return Response.status(Response.Status.OK).entity("E-mail sent").build();
    }



}

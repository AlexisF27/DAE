package ws;

import dtos.EmailDTO;
import dtos.EstructuraDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.ClienteBean;
import ejbs.EmailBean;
import ejbs.ProjetistaBean;
import ejbs.ProjetoBean;
import entities.Cliente;
import entities.Estructura;
import entities.Projetista;
import entities.Projeto;
import exceptions.MyEntityNotFoundException;


import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RolesAllowed({"Projetista"})
@Path("/projetistas")
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Produces({MediaType.APPLICATION_JSON})
public class ProjetistaServices {
    @EJB
    ProjetistaBean projetistaBean;

    @EJB
    ClienteBean clienteBean;

    @EJB
    ProjetoBean projetoBean;

    @EJB
    EmailBean emailBean;

    private ProjetistaDTO toDTO(Projetista projetista) {

        ProjetistaDTO projetistaDTO =  new ProjetistaDTO(
                projetista.getUsername(),
                projetista.getNome(),
                projetista.getPassword(),
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
                projetista.getPassword(),
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
    public Response sendEmail(EmailDTO email)
            throws MyEntityNotFoundException, MessagingException {

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + email.getMessage());

        Cliente cliente = clienteBean.findCliente(email.getClienteCode());
        if (cliente == null) {
            throw new MyEntityNotFoundException("Cliente com username '" + email.getClienteCode()
                    + "' nao se encontra.");
        }

        /*
        Projeto projeto = projetoBean.findProjeto(projetoCode);
        if (projeto == null) {
            throw new MyEntityNotFoundException("Projeto com id '" + projetoCode
                    + "' nao se encontra.");
        }
        */

        emailBean.send(cliente.getEmail(), email.getSubject(), email.getMessage());
        return Response.status(Response.Status.OK).entity("E-mail sent").build();
    }



}

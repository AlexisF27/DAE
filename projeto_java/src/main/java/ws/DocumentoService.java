package ws;

import dtos.DocumentoDTO;
import ejbs.ClienteBean;
import ejbs.DocumentoBean;
import entities.Cliente;
import entities.Documento;
import exceptions.MyEntityNotFoundException;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("documento")

public class DocumentoService {
    @EJB
    private DocumentoBean documentoBean;
    @EJB
    private ClienteBean clienteBean;

    private String getFilename(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {

            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
        System.out.println("Written: " + filename);
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(MultipartFormDataInput input) throws MyEntityNotFoundException, IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        // Get file data to save
        String username = uploadForm.get("username").get(0).getBodyAsString();
        Cliente cliente = clienteBean.findCliente(username);

        if(cliente == null) {
            throw new MyEntityNotFoundException("Cliente com username " + username + " nao se encontra.");
        }

        List<InputPart> inputParts = uploadForm.get("file");

        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String filename = getFilename(header);

                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                String path = System.getProperty("user.home") + File.separator + "uploads";
                File customDir = new File(path);

                if (!customDir.exists()) {
                    customDir.mkdir();
                }

                String filepath =  customDir.getCanonicalPath() + File.separator + filename;
                writeFile(bytes, filepath);

                documentoBean.create(cliente.getUsername(), path, filename);

                return Response.status(200).entity("Uploaded file name : " + filename).build();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GET
    @Path("download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") Integer id) throws MyEntityNotFoundException {
        Documento document = documentoBean.findDocumento(id);

        File fileDownload = new File(document.getFilepath() + File.separator + document.getFilename());

        Response.ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + document.getFilename());

        return response.build();
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DocumentoDTO> getDocuments(@PathParam("username") String username) throws MyEntityNotFoundException {
        Cliente cliente = clienteBean.findCliente(username);
        if(cliente == null) {
            throw new MyEntityNotFoundException("Cliente com username " + username + " nao se encontra.");
        }

        return documentsToDTOs(documentoBean.getClienteDocumentos(username));
    }


    DocumentoDTO toDTO(Documento documento) {
        return new DocumentoDTO(
                documento.getId(),
                documento.getFilepath(),
                documento.getFilename());
    }

    List<DocumentoDTO> documentsToDTOs(List<Documento> documentos) {
        return documentos.stream().map(this::toDTO).collect(Collectors.toList());
    }

}

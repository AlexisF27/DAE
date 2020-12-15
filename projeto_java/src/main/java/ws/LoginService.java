package ws;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import dtos.AuthDTO;
import ejbs.UserBean;
import entities.User;
import jwt.Jwt;
import ejbs.JwtBean;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.text.ParseException;
import java.util.logging.Logger;

@Path("/login")
public class LoginService {
    private static final Logger logger = Logger.getLogger(LoginService.class.getName());
    @EJB
    JwtBean jwtBean;
    @EJB
    UserBean userBean;
    @Context
    private SecurityContext securityContext;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON) // use MediaType.APPLICATION_FORM_URLENCODED for the http request files
    public Response login(AuthDTO authDTO) {

        try {
            User user = userBean.authenticate(authDTO.getUsername(), authDTO.getPassword());

            if (user == null) {
                throw new Exception("Can't authenticate with provided credentials");
            }

            logger.info("Generating JWT for user " + user.getUsername());

            String token = jwtBean.createJwt(user.getUsername(), new String[]{user.getClass().getSimpleName()});
            return Response.ok(new Jwt(token)).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {
        try {
            User user = userBean.authenticate(username, password); if (user != null) {
                if (user.getUsername() != null) {
                    logger.info("Generating JWT for user " + user.getUsername());
                }
                String token = jwtBean.createJwt(user.getUsername(), new String[]{user.getClass().getSimpleName()});
                return Response.ok(new Jwt(token)).build(); }
        } catch (Exception e) { logger.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build(); }
    @GET
    @Path("/claims")
    public Response demonstrateClaims(@HeaderParam("Authorization") String auth) {
        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                JWT j = JWTParser.parse(auth.substring(7));
                return Response.ok(j.getJWTClaimsSet().getClaims()).build(); //Note: nimbusds converts token expiration time to milliseconds
            } catch (ParseException e) {
                logger.warning(e.toString());
                return Response.status(400).build();
            }
        }
        return Response.status(204).build(); //no jwt means no claims to extract }
    }

    @GET
    @Path("user")
    public Response getClaims(@HeaderParam("Authorization") String authorization) {
        var principal = securityContext.getUserPrincipal();

        if (principal == null) {
            return Response.status(400).build();
        }

        try {
            var jwt = JWTParser.parse(authorization.substring(7)); // removes "Bearer "
            var claims = jwt.getJWTClaimsSet().getClaims();

            return Response.ok(claims).build();
        } catch (ParseException e) {
            logger.severe(e.getMessage());
            return Response.status(400).build();
        }
    }
}

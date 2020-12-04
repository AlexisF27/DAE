package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;


@Provider
public class MyIllegalArgumentExceptionMapper implements
        ExceptionMapper<MyIllegalArgumentException> {
    private static final Logger logger =
            Logger.getLogger("exceptions.MyIllegalArgumentException");
    @Override
    public Response toResponse(MyIllegalArgumentException e) {
        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);
        return
                Response.status(Response.Status.BAD_REQUEST).entity(errorMsg).build();
    }
}


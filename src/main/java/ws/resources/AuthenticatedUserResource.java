package ws.resources;

import ws.model.LookupView;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("authentication")
public class AuthenticatedUserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllLookupViewsList() {
        return "ok";
        //return Response.ok();
        //return Response.status(404).type("text/plain").entity("No such user found").build();

    }
}

package ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Contains a method which returns ok
 * if the user is authenticated correctly on login page
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
@Path("authentication")
public class AuthenticatedUserResource {
    //returns ok if the user has entered correct credentials on login page
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllLookupViewsList() {
        return "ok";
    }
}

package ws.resources;

import ws.model.LookupView;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The class contains a method that
 * returns a list of lookup views.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
@Path("lookupviews")
public class LookupViewResource {
    //returns a list of all lookup views available for the role of the logged in user in JSON format
    //when a GET request is performed.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LookupView> getAllLookupViewsList() {
        List<LookupView> lookupViewsList = DBCalls.getLookupViewsList();
        return lookupViewsList;
    }
}

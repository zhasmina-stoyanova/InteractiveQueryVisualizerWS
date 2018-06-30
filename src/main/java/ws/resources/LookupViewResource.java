package ws.resources;

import ws.model.LookupView;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("lookupviews")
public class LookupViewResource {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<LookupView> getAllLookupViewsList() {
        List<LookupView> lookupViewsList = DBCalls.getLookupViewsList();
        return lookupViewsList;
    }
}

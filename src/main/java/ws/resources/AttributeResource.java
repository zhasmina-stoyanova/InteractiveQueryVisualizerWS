package ws.resources;

import ws.model.Attribute;
import ws.model.LookupView;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("lookupviews")
public class AttributeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/attributes")
    public List<Attribute> getAttributesList(@PathParam("lookupview") String lookupview) {
        List<Attribute> attributeList = DBCalls.getAttributesList(lookupview);
        return attributeList;
    }
}

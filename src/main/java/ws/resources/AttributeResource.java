package ws.resources;

import ws.model.Attribute;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The class contains a method that
 * returns a list of attributes for a given lookup view.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
@Path("lookupviews")
public class AttributeResource {
    //returns a list of all attributes for a given lookup view in JSON format
    //when a GET request is performed and the name of the lookup view provided.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/attributes")
    public List<Attribute> getAttributesList(@PathParam("lookupview") String lookupview) {
        List<Attribute> attributeList = DBCalls.getAttributesList(lookupview);
        return attributeList;
    }
}

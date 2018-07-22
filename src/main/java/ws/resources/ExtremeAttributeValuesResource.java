package ws.resources;

import ws.model.ExtremeAttributeValues;
import ws.repository.DBCalls;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("lookupviews")
public class ExtremeAttributeValuesResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/extremes")
    public ExtremeAttributeValues getTableDataAttributeValues(@PathParam("lookupview") String lookupview,
                                                              @QueryParam("attribute") String attribute) {
        ExtremeAttributeValues data = null;
        data = DBCalls.getAttributeExtremes(lookupview, attribute);
        return data;
    }
}

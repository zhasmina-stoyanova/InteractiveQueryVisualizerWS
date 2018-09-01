package ws.resources;

import ws.model.ExtremeAttributeValues;
import ws.repository.DBCalls;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * The class contains a method that
 * returns an instance of the class
 * that represents the min and max values of each attribute.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
@Path("lookupviews")
public class ExtremeAttributeValuesResource {
    //returns an instance of the class that represents the min and max values of given attribute in JSON format
    //when a GET request is performed and the names of the lookup view and the attribute are provided.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/extremes")
    public ExtremeAttributeValues getTableDataAttributeValues(@PathParam("lookupview") String lookupview,
                                                              @QueryParam("attribute") String attribute) {
        //single quotations around each attribute for attributes with space in the name like 'zip code'
        String attributesSingleQuotes = "`" + attribute + "`";
        ExtremeAttributeValues extremeValuesObject = DBCalls.getAttributeExtremes(lookupview, attributesSingleQuotes);
        return extremeValuesObject;
    }
}

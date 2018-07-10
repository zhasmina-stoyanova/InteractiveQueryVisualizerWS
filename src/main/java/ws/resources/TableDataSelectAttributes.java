package ws.resources;

import ws.model.TableDataRowItem;
import ws.repository.DBCalls;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("lookupviews")
public class TableDataSelectAttributes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/select")
    public List<TableDataRowItem> getTableDataAttributeValues(@PathParam("lookupview") String lookupview,
                                                            @QueryParam("attributes") String attributes) {
        String arrt = attributes;
        List<TableDataRowItem> data = DBCalls.getTableDataAttributes(lookupview, attributes);
        return data;
    }
}

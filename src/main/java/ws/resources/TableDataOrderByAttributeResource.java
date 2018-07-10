package ws.resources;

import ws.model.TableDataRowItem;
import ws.repository.DBCalls;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("lookupviews")
public class TableDataOrderByAttributeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/query")
    public List<TableDataRowItem> getTableDataOrderedValues(@PathParam("lookupview") String lookupview,
                                                           @QueryParam("orderBy") String orderBy) {
        String orderByClause = orderBy;
        String[] parts = orderByClause.split(":");
        String orderByAttribute = parts[0];
        //asc or desc order
        String order = parts[1];

        List<TableDataRowItem> data = DBCalls.getTableDataOrdered(lookupview, orderByAttribute, order);
        return data;
    }
}

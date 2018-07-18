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
                                                              @QueryParam("attributes") String attributes, @QueryParam("orderBy") String orderBy) {
        List<TableDataRowItem> data = null;
        //have order by clause
        if (orderBy != null) {
            String orderByClause = orderBy;
            String[] parts = orderByClause.split(":");
            String orderByAttribute = parts[0];
            //asc or desc order
            String order = parts[1];
            data = DBCalls.getTableDataAttributesOrdered(lookupview, attributes, orderByAttribute, order);
            return data;
        } else {
            //just selecting attributes without order by clause
            data = DBCalls.getTableDataAttributes(lookupview, attributes);
        }
        return data;
    }
}

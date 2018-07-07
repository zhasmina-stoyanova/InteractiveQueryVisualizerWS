package ws.resources;

import ws.model.TableDataRowItem;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("lookupviews")
public class TableDataResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}")
    public List<TableDataRowItem> getTableDataValues(@PathParam("lookupview") String lookupview) {
        List<TableDataRowItem> data = DBCalls.getTableData(lookupview);
        return data;
    }
}

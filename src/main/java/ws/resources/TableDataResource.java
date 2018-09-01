package ws.resources;

import ws.model.TableRow;
import ws.repository.DBCalls;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The class contains a method that
 * returns the data for the given database table
 * represented as a list of rows.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
@Path("lookupviews")
public class TableDataResource {
    //returns a list of TableRow objects in JSON format
    //when a GET request is performed and the name of the lookup view is provided.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}")
    public List<TableRow> getTableDataValues(@PathParam("lookupview") String lookupview) {
        List<TableRow> tableRowList = DBCalls.getTableData(lookupview);
        return tableRowList;
    }
}

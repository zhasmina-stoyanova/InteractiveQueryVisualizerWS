package ws.resources;

import ws.model.TableRow;
import ws.repository.DBCalls;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The class contains a method that
 * returns the data for the given database table
 * represented as a list of rows. The difference between the
 * TableDataResource class is that here the names of the attributes,
 * the where clause and the orderBy attribute must be provided.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
@Path("lookupviews")
public class TableDataSelectAttributesResource {
    //returns a list of TableRow objects in JSON format
    //when a GET request is performed and the name of the lookup view, together with
    //the names of the attributes, the where clause and the orderBy attribute are provided.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/select")
    public List<TableRow> getTableDataAttributeValues(@PathParam("lookupview") String lookupview,
                                                      @QueryParam("attributes") String attributes, @QueryParam("where") String where, @QueryParam("orderBy") String orderBy) {
        //builds the where clause from the request
        StringBuilder whereClause = new StringBuilder();
        //if no where clause is specified
        if (where == null) {
            whereClause.append("1=1");
        } else {
            String[] whereList = where.split(",");
            for (String aWhereList : whereList) {
                //example SID=1;3 - values for SID are between 1 and 3
                //range of values
                if (aWhereList.contains(";")) {
                    int indexEqualsSymbol = aWhereList.indexOf("=");
                    int separatorSymbol = aWhereList.indexOf(";");
                    String attributeName = aWhereList.substring(0, indexEqualsSymbol);
                    String minRangeValue = aWhereList.substring(indexEqualsSymbol + 1, separatorSymbol);
                    String maxRangeValue = aWhereList.substring(separatorSymbol + 1, aWhereList.length());
                    whereClause.append(" and ").append(attributeName).append(" >= ").append(minRangeValue).append(" and ").append(attributeName).append(" <= ").append(maxRangeValue).append(" ");
                } else {
                    //attribute value needs to be put in single quotation marks
                    int indexEqualsSymbol = aWhereList.indexOf("=");
                    String attributeName = aWhereList.substring(0, indexEqualsSymbol);
                    String attributeValue = "'" + aWhereList.substring(indexEqualsSymbol + 1, aWhereList.length()) + "'";
                    whereClause.append(" and ").append(attributeName + "=" + attributeValue);
                }
            }
            whereClause = whereClause.delete(0, 5);
        }

        //single quotations around each attribute for attributes with space in the name like 'zip code''
        StringBuilder attributesWithSingleQuotes = new StringBuilder();
        String[] attributesString = attributes.split(",");
        for (String anAttributesString : attributesString) {
            attributesWithSingleQuotes.append("`").append(anAttributesString).append("`,");
        }
        attributesWithSingleQuotes.deleteCharAt(attributesWithSingleQuotes.length() - 1);

        //gets table data
        List<TableRow> tableData;
        //have order by clause
        if (orderBy != null) {
            //parts[0] - order by attribute, parts[1] - asc or desc order
            String[] parts = orderBy.split(":");
            String orderByAttribute = parts[0];
            //asc or desc order
            String order = parts[1];
            tableData = DBCalls.getTableDataOrdered(lookupview, attributesWithSingleQuotes.toString(), orderByAttribute, whereClause.toString(), order);
            return tableData;
        } else {
            //selecting attributes without order by clause
            tableData = DBCalls.getTableData(lookupview, attributesWithSingleQuotes.toString(), whereClause.toString());
        }
        return tableData;
    }
}

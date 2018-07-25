package ws.resources;

import ws.model.TableDataRowItem;
import ws.repository.DBCalls;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("lookupviews")
public class TableDataSelectAttributes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{lookupview}/select")
    public List<TableDataRowItem> getTableDataAttributeValues(@PathParam("lookupview") String lookupview,
                                                              @QueryParam("attributes") String attributes, @QueryParam("where") String where, @QueryParam("orderBy") String orderBy) {
        StringBuilder whereClause = new StringBuilder();
        if(where == null){
            whereClause.append("1=1");
        }else{
            String str = where;
            List<String> whereList = Arrays.asList(str.split(","));
            for(int i = 0; i < whereList.size(); i++){
                //example SID=1;3 - values for SID are between 1 and 3
                if(whereList.get(i).contains(";")){
                    String param = whereList.get(i);
                    int indexEqualsSymbol = param.indexOf("=");
                    int separatorSymbol = param.indexOf(";");
                    String attributeName = param.substring(0, indexEqualsSymbol);
                    String startValueInRange = param.substring(indexEqualsSymbol + 1, separatorSymbol);
                    String endValueInRange = param.substring(separatorSymbol + 1, param.length());
                    whereClause.append(" and " + attributeName + " >= " + startValueInRange + " and " + attributeName + " <= " + endValueInRange + " ");
                }else {
                    whereClause.append(" and " + whereList.get(i));
                }
            }
            whereClause = whereClause.delete(0, 5);
        }
        List<TableDataRowItem> data = null;
        //have order by clause
        if (orderBy != null) {
            String orderByClause = orderBy;
            String[] parts = orderByClause.split(":");
            String orderByAttribute = parts[0];
            //asc or desc order
            String order = parts[1];
            data = DBCalls.getTableDataAttributesOrdered(lookupview, attributes, orderByAttribute, whereClause.toString(), order);
            return data;
        } else {
            //just selecting attributes without order by clause
            data = DBCalls.getTableDataAttributes(lookupview, attributes, whereClause.toString());
        }
        return data;
    }
}

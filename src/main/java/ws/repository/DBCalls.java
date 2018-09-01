package ws.repository;

import ws.model.*;

import java.sql.*;
import java.util.*;

public class DBCalls {
    //the authenticated user
    private static String userId;

    /**
     * Returns a list of lookup views available for the role of the logged in user
     */
    public static List<LookupView> getLookupViewsList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LookupView> list = new ArrayList<>();

        try {
            conn = Repository.initializeConnection();
            String query = "select lv.name\n" +
                    "from user u\n" +
                    "inner join user_in_role ur on u.id = ur.user_id\n" +
                    "inner join role r on ur.role_id = r.id\n" +
                    "inner join lookup_view_in_role lvr on r.id = lvr.role_id\n" +
                    "inner join lookup_view lv on lvr.lookup_view_id = lv.id\n" +
                    "and u.id = '" + userId + "' order by lv.name";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LookupView view = new LookupView(rs.getString("name"));
                list.add(view);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }

        return list;
    }

    /**
     * Authenticates the user's credentials.
     */
    public static boolean authenticateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Repository.initializeConnection();
            String query = "select id from user where username=? and password=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getString("id");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
        return false;
    }

    /**
     * Returns the name of the schema a given lookup view belongs to.
     */
    private static String getLookupViewSchema(String lookupview) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String view = null;
        try {
            conn = Repository.initializeConnection();
            String query = "select schema_name from lookup_view where name=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, lookupview);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                view = rs.getString("schema_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
        return view;
    }

    /**
     * Returns a list of attributes of a given lookup view.
     * Each attribute has information about its name and type.
     */
    public static List<Attribute> getAttributesList(String lookupview) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Attribute> list = new ArrayList<>();
        String schema = getLookupViewSchema(lookupview);

        try {
            conn = Repository.initializeConnection();
            String query = "SELECT column_name, data_type\n" +
                    "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                    "where table_name = '" + lookupview + "' and table_schema = '" + schema +
                    "' order by column_name";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Attribute attribute = new Attribute(rs.getString("column_name"), rs.getString("data_type"));
                list.add(attribute);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }

        return list;
    }

    /**
     * Returns a list of TableRow objects that contain all the table data.
     * for the implementation fetches only 30 records not to slow down the performance
     */
    public static List<TableRow> getTableData(String lookupview) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TableRow> rowList = new ArrayList<>();
        String schema = getLookupViewSchema(lookupview);

        try {
            conn = Repository.initializeConnection();
            String query = "SELECT * FROM " + schema + ".`" + lookupview + "` limit 30";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();


            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                List<TableCell> listColumn = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    TableCell column = new TableCell(metaData.getColumnLabel(i), (rs.getObject(i) != null ? "" + rs.getObject(i) : ""));
                    listColumn.add(column);
                }

                TableRow rowItem = new TableRow(listColumn);
                rowList.add(rowItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
        return rowList;
    }

    /**
     * Returns a list of TableRow objects that represent the table data with order by clause.
     * for the implementation fetches only 30 records not to slow down the performance
     */
    public static List<TableRow> getTableDataOrdered(String lookupview, String attributes, String orderByAttribute, String where, String order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TableRow> rowList = new ArrayList<>();
        String schema = getLookupViewSchema(lookupview);

        try {
            conn = Repository.initializeConnection();
            String query = "SELECT " + attributes + " FROM " + schema + ".`" + lookupview + "` where " + where + " order by " + orderByAttribute + " " + order + " limit 30";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();


            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                List<TableCell> listColumn = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    TableCell column = new TableCell(metaData.getColumnLabel(i), rs.getObject(i).toString());
                    listColumn.add(column);
                }

                TableRow rowItem = new TableRow(listColumn);
                rowList.add(rowItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
        return rowList;
    }

    /**
     * Returns a list of TableRow objects that represent the table data without order by clause.
     * for the implementation fetches only 30 records not to slow down the performance
     */
    public static List<TableRow> getTableData(String lookupview, String attributes, String where) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TableRow> rowList = new ArrayList<>();
        String schema = getLookupViewSchema(lookupview);

        try {
            conn = Repository.initializeConnection();
            String query = "SELECT " + attributes + " FROM " + schema + ".`" + lookupview + "` where " + where + " limit 30";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                List<TableCell> listColumn = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    TableCell column = new TableCell(metaData.getColumnLabel(i), rs.getObject(i).toString());
                    listColumn.add(column);
                }

                TableRow rowItem = new TableRow(listColumn);
                rowList.add(rowItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
        return rowList;
    }

    /**
     * Returns an instance of the class that contains the min and max values of each attribute
     * for the implementation fetches only 30 records not to slow down the performance
     */
    public static ExtremeAttributeValues getAttributeExtremes(String lookupview, String attribute) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ExtremeAttributeValues extremes = null;
        String schema = getLookupViewSchema(lookupview);

        try {
            conn = Repository.initializeConnection();
            String query = "SELECT min(" + attribute + ") as minimum , max(" + attribute + ") as maximum FROM " + schema + ".`" + lookupview + "`";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                extremes = new ExtremeAttributeValues(rs.getString("minimum"), rs.getString("maximum"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
        return extremes;
    }

    /**
     * Closes the ResultSet.
     */
    private static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the prepared statement.
     */
    private static void closePreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the connection.
     */
    private static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

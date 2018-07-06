package ws.repository;

import ws.model.LookupView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCalls {

    //get all lookup views
    public static List<LookupView> getLookupViewsList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LookupView> list = new ArrayList<LookupView>();

        try {
            conn = Repository.initializeConnection();
            String query = "SELECT name from lookup_views";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LookupView notification = new LookupView();
                notification.setName(rs.getString("name"));

                list.add(notification);
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
     * Close the connection.
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

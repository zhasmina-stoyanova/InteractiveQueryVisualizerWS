package ws.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {

    //MariaDB connection
    private static final String URL = "jdbc:mysql://zs30.host.cs.st-andrews.ac.uk:3306/zs30_sakila";
    private static final String USERNAME = "zs30";
    private static final String PASSWORD = "F14VMP7v.d47wg";

    public static Connection initializeConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}

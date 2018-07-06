package ws.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {

    //MariaDB connection
    //local db
    private static final String URL = "jdbc:mysql://localhost:3306/zs30_query_visualizer";
    private static final String USERNAME = "local_user";
    private static final String PASSWORD = "system";

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

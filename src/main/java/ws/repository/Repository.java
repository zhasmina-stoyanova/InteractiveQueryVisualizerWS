package ws.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class that is used to initialize connection to
 * MariaDB by providing URL and user credentials.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class Repository {

    //MariaDB connection
    private static final String URL = "jdbc:mysql://zs30.host.cs.st-andrews.ac.uk:3306/zs30_dba";
    private static final String USERNAME = "zs30";
    private static final String PASSWORD = "F14VMP7v.d47wg";

    //initializes the database connection with mysql jdbc driver
    public static Connection initializeConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //tries to establish a connection to the database with the given URL and credentials.
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}

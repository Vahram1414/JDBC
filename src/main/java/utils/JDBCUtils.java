package utils;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static final String DATABASE_URL_KEY = "jdbc:mysql://127.0.0.1:3306/crud_jdbc";

    static final String USER_KEY = "root";
    static final String PASSWORD_KEY = "12345";
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static Connection getConnectJDBC() throws SQLException {

        System.out.println("Registering JDBC driver...");
        if(connection == null) {

        try {
            connection = DriverManager.getConnection(
                    DATABASE_URL_KEY,
                    USER_KEY,
                    PASSWORD_KEY
            );
            System.out.println("Creating Database connection...");
        } catch (SQLException e) {
            System.out.println("IN connection exception: " + e.getMessage());
        }}
        return connection;
    }

}

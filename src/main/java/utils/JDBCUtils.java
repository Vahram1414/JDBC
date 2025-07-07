package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static final String DATABASE_URL_KEY = "db.url";

    static final String USER_KEY = "db.user";
    static final String PASSWORD_KEY = "db.password";

    public static Connection getConnectJDBC() {

        System.out.println("Registering JDBC driver...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(DATABASE_URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
            System.out.println("Creating Database connection...");
        } catch (SQLException e) {
            System.out.println("IN connection exception: " + e.getMessage());
        }
        return connection;
    }

}

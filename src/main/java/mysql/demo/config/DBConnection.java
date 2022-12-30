package mysql.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static Connection dbConnection;

    public static Connection getConnection() {
        if (dbConnection == null) {
            try {
                dbConnection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s",
                        Config.getProperty("host"), Config.getProperty("port"), Config.getProperty("db")),
                        Config.getProperty("user"), Config.getProperty("password"));
                dbConnection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dbConnection;
    }

}

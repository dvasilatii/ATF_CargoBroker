package org.cargobroker.utils;

import lombok.extern.log4j.Log4j2;
import java.sql.Connection;
import java.sql.DriverManager;

@Log4j2
public class DBUtils {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            String url = Utils.getProperty("dbURL");
            String username = Utils.getProperty("dbUser");
            String password = Utils.getProperty("dbPass");

            log.info("Connecting database ...");

            try {
                connection = DriverManager.getConnection(url, username, password);
                log.info("DB connection is established");
            } catch (Exception e) {
                throw new RuntimeException("Cannot connect the database!", e);
            }
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
            throw new RuntimeException("Cannot close connection", e);
        }
    }
}
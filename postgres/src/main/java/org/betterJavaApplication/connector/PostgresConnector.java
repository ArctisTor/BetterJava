package org.betterJavaApplication.connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

@Component
public class PostgresConnector {

    @Value("${postgres.host}")
    private String hostName;

    @Value("${postgres.port}")
    private String port;

    @Value("${postgres.user}")
    private String user;

    @Value("${postgres.password}")
    private String password;

    private Connection postgresConnection;

    public void connectToPostgres() {
        String url = "jdbc:postgresql://"+hostName+":"+port+"/VTubers";
        final Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        try {
            if (this.postgresConnection == null || postgresConnection.isClosed()) {
                this.postgresConnection = DriverManager.getConnection(url, props);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database " + Arrays.toString(e.getStackTrace()));
        }
//        try (Connection con = DriverManager.getConnection(url, props)) {
//            System.out.println(con.getMetaData().getDatabaseProductVersion());
//        } catch (SQLException e) {
//            System.out.println("Error connecting to database " + Arrays.toString(e.getStackTrace()));
//        }
    }

    public void closeConnection() {
        try {
            if (postgresConnection != null && !postgresConnection.isClosed()) {
                postgresConnection.close();
            } else {
                System.out.println("Was not connected to database from the start");
            }
        } catch (SQLException e) {
            System.out.println("Error disconnecting from database " + Arrays.toString(e.getStackTrace()));
        }
    }

    public Connection getPostgresConnection() {
        return postgresConnection;
    }
}

package org.betterJavaApplication.connector;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.service.heartbeat.DatabaseTestConnectionService;

@Component
public class PostgresConnector implements DatabaseTestConnectionService {

    private final DataSource vtubersDataSource;
    private final DataSource meadDataSource;
    private final Gson gson = new Gson();

    public PostgresConnector(
            @Qualifier("vtubersDataSource") DataSource vtubersDataSource,
            @Qualifier("meadDataSource") DataSource meadDataSource) {
        this.vtubersDataSource = vtubersDataSource;
        this.meadDataSource = meadDataSource;
    }

    private JsonObject testConnection(DataSource dataSource) {
        JsonObject status = new JsonObject();

        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();

            status.add("database-connected", gson.toJsonTree(conn.isValid(2)));
            status.add("database-type", gson.toJsonTree(meta.getDatabaseProductName()));
            status.add("database-version", gson.toJsonTree(meta.getDatabaseProductVersion()));
        } catch (SQLException e) {
            status.add("database-connected", gson.toJsonTree(false));
            status.add("database-error-message", gson.toJsonTree(e.getMessage()));
        }

        return status;
    }

    public JsonObject testVtubersConnection() {
        return testConnection(vtubersDataSource);
    }

    public JsonObject testMeadConnection() {
        return testConnection(meadDataSource);
    }

}

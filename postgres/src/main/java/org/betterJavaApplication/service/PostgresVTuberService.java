package org.betterJavaApplication.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.betterJavaApplication.connector.PostgresConnector;
import org.betterJavaApplication.constants.PostgresQueryStatements;
import org.constants.VTuberConstants;
import org.object.Talent;
import org.service.VTuberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostgresVTuberService implements VTuberService {

    private final PostgresConnector postgresConnector;

    @Autowired
    public PostgresVTuberService(PostgresConnector postgresConnector) {
        this.postgresConnector = postgresConnector;
    }

    @PostConstruct
    public void connect() {
        this.postgresConnector.connectToPostgres();
    }

    @PreDestroy
    public void cleanup() {
        this.postgresConnector.closeConnection();
    }

    @Override
    public List<Talent> getAllVTubers() {

        try (Statement stmt = this.postgresConnector.getPostgresConnection().createStatement()){
            return vtuberResultSetMapping(stmt.executeQuery(PostgresQueryStatements.SELECT_ALL_VTUBERS));
        } catch (SQLException e) {
            System.out.println("Error with querying database: " + e.getMessage());
        }

        return List.of();
    }


    private List<Talent> vtuberResultSetMapping(ResultSet rs) throws SQLException {
        List<Talent> vTuberList = new ArrayList<>();

        while(rs.next()) {
            Talent vTuber = new Talent();
            vTuber.setId(rs.getString(VTuberConstants.TALENT_ID));
            vTuber.setName(rs.getString(VTuberConstants.TALENT_NAME));
            vTuber.setDebut(rs.getDate(VTuberConstants.TALENT_DEBUT).toString());
            vTuber.setBirthday(rs.getDate(VTuberConstants.TALENT_BIRTHDAY).toString());
            vTuber.setOrganization(rs.getString(VTuberConstants.TALENT_ORGANIZATION));
            vTuber.setUnit(rs.getString(VTuberConstants.TALENT_UNIT));
            vTuber.setHeight(rs.getDouble(VTuberConstants.TALENT_HEIGHT));
            vTuber.setFanName(rs.getString(VTuberConstants.TALENT_FAN_NAME));
            vTuberList.add(vTuber);
        }

        return vTuberList;
    }
}

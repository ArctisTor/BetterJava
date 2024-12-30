package org.betterJavaApplication.service;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.betterJavaApplication.connector.PostgresConnector;
import org.betterJavaApplication.constants.PostgresQueryStatements;
import org.betterJavaApplication.entity.OrganizationEntity;
import org.betterJavaApplication.entity.TalentEntity;
import org.betterJavaApplication.repository.OrganizationRepository;
import org.betterJavaApplication.repository.TalentRepository;
import org.betterJavaApplication.utils.EntityToObjectMapper;
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
import java.util.Optional;

@Service
public class PostgresVTuberService implements VTuberService {

    private final PostgresConnector postgresConnector;
    private final TalentRepository talentRepository;
    private final OrganizationRepository organizationRepository;
    private final Gson gson = new Gson();

    @Autowired
    public PostgresVTuberService(PostgresConnector postgresConnector, TalentRepository talentRepository, OrganizationRepository organizationRepository) {
        this.postgresConnector = postgresConnector;
        this.talentRepository = talentRepository;
        this.organizationRepository = organizationRepository;
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

//        try (Statement stmt = this.postgresConnector.getPostgresConnection().createStatement()){
//            return vtuberResultSetMapping(stmt.executeQuery(PostgresQueryStatements.SELECT_ALL_VTUBERS));
//        } catch (SQLException e) {
//            System.out.println("Error with querying database: " + e.getMessage());
//        }
        List<OrganizationEntity> organizationEntities = this.organizationRepository.findAll();
        List<Talent> talentList = new ArrayList<>();
        Iterable<TalentEntity> itr = this.talentRepository.findAll();
        itr.forEach(talentEntity -> {
            Optional<OrganizationEntity> matchingOrganization = organizationEntities.stream()
                    .filter(organizationEntity -> organizationEntity.getId().equals(talentEntity.getTalent_organization()))
                    .findFirst();
            matchingOrganization.ifPresent(organizationEntity -> {
                talentEntity.setTalent_organization(organizationEntity.getName());
            });
            talentList.add(EntityToObjectMapper.toTalentModel(talentEntity));
        });

        return talentList;
    }

    @Override
    public List<Talent> getByOrganization(String orgName) {
        List<Talent> talentList = new ArrayList<>();
        Optional<OrganizationEntity> organizationOptional = organizationRepository.findByName(orgName);
        if (organizationOptional.isPresent()) {
            String orgId = organizationOptional.get().getId();
            List<TalentEntity> list = this.talentRepository.findByOrganization(orgId);
            list.forEach(talentEntity -> {
                talentList.add(EntityToObjectMapper.toTalentModel(talentEntity));
            });
        } else {
            throw new RuntimeException(String.format("No Talent found for organization: %s", orgName));
        }
        return talentList;
    }

    @Override
    public Talent getShortestTalent() {
        Optional<TalentEntity> shortestTalent = this.talentRepository.findShortestTalent();
        if (shortestTalent.isPresent()) {
            Optional<OrganizationEntity> org = this.organizationRepository.findById(shortestTalent.get().getTalent_organization());
            org.ifPresent(organizationEntity -> shortestTalent.get().setTalent_organization(organizationEntity.getName()));
            return EntityToObjectMapper.toTalentModel(shortestTalent.get());
        } else {
            throw new RuntimeException("No talents found");
        }

    }

    @Override
    public Talent debutVTuberTalent(Talent newTalent) {
        try {
            TalentEntity debutTalent = this.talentRepository.save(new TalentEntity(newTalent));
            return EntityToObjectMapper.toTalentModel(debutTalent);
        } catch (Exception e) {
            throw new RuntimeException("There was an error debuting the talent." , e);
        }
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

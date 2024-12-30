package org.betterJavaApplication.service;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.betterJavaApplication.connector.PostgresConnector;
import org.betterJavaApplication.entity.OrganizationEntity;
import org.betterJavaApplication.repository.OrganizationRepository;
import org.betterJavaApplication.utils.EntityToObjectMapper;
import org.object.Organization;
import org.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostgresOrganizationService implements OrganizationService {

    private final PostgresConnector postgresConnector;
    private final OrganizationRepository organizationRepository;
    private final Gson gson = new Gson();


    @Autowired
    public PostgresOrganizationService(PostgresConnector postgresConnector, OrganizationRepository organizationRepository) {
        this.postgresConnector = postgresConnector;
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
    public List<Organization> getAllOrganizations() {
        List<Organization> organizationList = new ArrayList<>();
        Iterable<OrganizationEntity> organizationEntities = this.organizationRepository.findAll();
        organizationEntities.forEach(organizationEntity -> {
            organizationList.add(EntityToObjectMapper.toOrganizationModel(organizationEntity));
        });
        return organizationList;
    }

    @Override
    public Organization getOrganizationByName(String name) {
        Optional<OrganizationEntity> organizationEntityOptional = this.organizationRepository.findByName(name);
        if (organizationEntityOptional.isPresent()) {
            return EntityToObjectMapper.toOrganizationModel(organizationEntityOptional.get());
        } else {
            throw new RuntimeException(String.format("No Organizations found for: %s", name));
        }
    }

    @Override
    public Organization getOrganizationById(String id) {
        Optional<OrganizationEntity> organizationEntityOptional = this.organizationRepository.findById(id);
        if (organizationEntityOptional.isPresent()) {
            return EntityToObjectMapper.toOrganizationModel(organizationEntityOptional.get());
        } else {
            throw new RuntimeException(String.format("No Organizations found for: %s", id));
        }
    }

    public List<Organization> organizationResultSetMapping(ResultSet resultSet) throws SQLException {
        List<Organization> organizations = new ArrayList<>();

        // Iterate over the result set to map the rows to Organization objects
        while (resultSet.next()) {
            Organization org = new Organization();
            org.setId(resultSet.getString("id"));
            org.setName(resultSet.getString("name"));
            org.setOwner(resultSet.getString("owner"));
            org.setWebsite(resultSet.getString("website"));
            org.setFoundedDate(resultSet.getString("founded"));
            org.setIndustry(resultSet.getString("industry"));
            org.setFounder(resultSet.getString("founder"));

            // Assuming 'brands' is a comma-separated string in the database. Adjust as needed
            String brandsString = resultSet.getString("brands");
            String[] brands = new String[0];


            if (brandsString != null && !brandsString.isEmpty()) {
                // Clean the malformed brands array
                brandsString = brandsString.replace("{", "").replace("}", "").trim(); // Remove curly braces
                brandsString = brandsString.replace("\"", "").trim();  // Remove any extra quotes

                // Split the string into an array based on commas and trim each brand name
                brands = brandsString.split("\",\"");
                for (int i = 0; i < brands.length; i++) {
                    brands[i] = brands[i].trim(); // Clean up any extra spaces
                }
            }
            org.setBrands(brands);

            // Add the organization to the list
            organizations.add(org);
        }

        return organizations;
    }
}

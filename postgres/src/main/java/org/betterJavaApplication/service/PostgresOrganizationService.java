package org.betterJavaApplication.service;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.betterJavaApplication.connector.PostgresConnector;
import org.betterJavaApplication.entity.vtubers.OrganizationEntity;
import org.betterJavaApplication.repository.vtubers.OrganizationRepository;
import org.betterJavaApplication.utils.EntityToObjectMapper;
import org.object.Organization;
import org.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.validator.OrganizationValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostgresOrganizationService implements OrganizationService {

    private final PostgresConnector postgresConnector;
    private final OrganizationRepository organizationRepository;
    private final OrganizationValidator organizationValidator = new OrganizationValidator();;

    @Autowired
    public PostgresOrganizationService(PostgresConnector postgresConnector,
            OrganizationRepository organizationRepository) {
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
    public List<Organization> getAllOrganizations(int limit, int offset) {
        List<Organization> organizationList = new ArrayList<>();
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Iterable<OrganizationEntity> organizationEntities = this.organizationRepository.findAll(pageable).getContent();
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

    @Override
    public Organization addOrganization(Organization organization) {
        if (this.organizationValidator.isOrganizationValid(organization)) {
            // TODO will need to implement this
        }
        return null;
    }
}

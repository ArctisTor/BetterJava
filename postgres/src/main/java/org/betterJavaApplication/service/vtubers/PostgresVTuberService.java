package org.betterJavaApplication.service.vtubers;

import org.betterJavaApplication.entity.vtubers.TalentEntity;
import org.betterJavaApplication.repository.vtubers.TalentRepository;
import org.betterJavaApplication.utils.EntityToObjectMapper;
import org.object.Organization;
import org.object.Talent;
import org.service.vtuber.OrganizationService;
import org.service.vtuber.VTuberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.validator.OrganizationValidator;
import org.validator.TalentValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostgresVTuberService implements VTuberService {

    private final TalentRepository talentRepository;
    private final OrganizationService organizationService;
    private final OrganizationValidator organizationValidator = new OrganizationValidator();
    private final TalentValidator talentValidator;

    @Autowired
    public PostgresVTuberService(TalentRepository talentRepository, OrganizationService organizationService) {
        this.talentRepository = talentRepository;
        this.organizationService = organizationService;
        this.talentValidator = new TalentValidator(this.organizationService);
    }

    @Override
    public List<Talent> getAllVTubers(int limit, int offset) {
        List<Organization> organizationList = this.organizationService.getAllOrganizations(Integer.MAX_VALUE, 0);
        List<Talent> talentList = new ArrayList<>();
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Iterable<TalentEntity> itr = this.talentRepository.findAll(pageable).getContent();
        itr.forEach(talentEntity -> {
            Optional<Organization> matchingOrganization = organizationList.stream()
                    .filter(organization -> organization.getId().equals(talentEntity.getTalent_organization()))
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
        Organization organization = organizationService.getOrganizationByName(orgName);
        if (organizationValidator.isOrganizationValid(organization)) {
            String orgId = organization.getId();
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
    public Talent getVTuberTalentById(String id) {
        Optional<TalentEntity> foundTalent = this.talentRepository.findById(id);
        return foundTalent.map(EntityToObjectMapper::toTalentModel).orElse(null);
    }

    @Override
    public List<Talent> getVTuberTalentByName(String name) {
        List<TalentEntity> foundTalents = this.talentRepository.findByName(name);
        List<Talent> talentList = new ArrayList<>();
        foundTalents.forEach(talentEntity -> {
            talentList.add(EntityToObjectMapper.toTalentModel(talentEntity));
        });
        return talentList;
    }

    @Override
    public Talent getShortestTalent() {
        Optional<TalentEntity> shortestTalent = this.talentRepository.findShortestTalent();
        if (shortestTalent.isPresent()) {
            Organization org = this.organizationService
                    .getOrganizationById(shortestTalent.get().getTalent_organization());
            if (organizationValidator.isOrganizationValid(org)) {
                shortestTalent.get().setTalent_organization(org.getName());
            } else {
                throw new RuntimeException(
                        String.format("No Organizations found for: %s", shortestTalent.get().getName()));
            }
            return EntityToObjectMapper.toTalentModel(shortestTalent.get());
        } else {
            throw new RuntimeException("No talents found");
        }

    }

    @Override
    public Talent debutVTuberTalent(Talent newTalent) {
        if (!this.talentValidator.isTalentValid(newTalent)) {
            throw new IllegalArgumentException("Talent was not a valid format.");
        }

        try {
            if (newTalent.getId().isBlank()) {
                newTalent.setId(String.valueOf(UUID.randomUUID()));
            }
            TalentEntity debutTalent = this.talentRepository.save(new TalentEntity(newTalent));
            return EntityToObjectMapper.toTalentModel(debutTalent);
        } catch (Exception e) {
            throw new RuntimeException("There was an error debuting the talent.", e);
        }
    }

    @Override
    public Talent updateVTuberTalent(Talent updateTalent) {
        if (!this.talentValidator.isTalentValid(updateTalent)
                || updateTalent.getId() == null || updateTalent.getId().isBlank()) {
            throw new IllegalArgumentException("Talent was not a valid format.");
        }

        // check to see if it exists
        Talent checkTalent = this.getVTuberTalentById(updateTalent.getId());
        if (checkTalent == null) {
            throw new IllegalArgumentException(
                    String.format("Talent was not found with id %s", updateTalent.getId()));
        }

        return null;
    }

}

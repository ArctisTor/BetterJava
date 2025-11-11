package org.betterJavaApplication.utils;

import com.google.gson.JsonObject;
import org.betterJavaApplication.entity.vtubers.OrganizationEntity;
import org.betterJavaApplication.entity.vtubers.TalentEntity;
import org.object.Organization;
import org.object.Talent;

public class EntityToObjectMapper {

    // Converts a Talent object to TalentEntity
    public static TalentEntity toTalentEntity(Talent talent) {
        return new TalentEntity(talent);
    }

    // Converts a TalentEntity object to a Talent object
    public static Talent toTalentModel(TalentEntity talentEntity) {
        Talent talent = new Talent();
        talent.setId(talentEntity.getId());
        talent.setName(talentEntity.getName());
        talent.setDebut(talentEntity.getTalent_debut());
        talent.setBirthday(talentEntity.getTalent_birthday());
        talent.setOrganization(talentEntity.getTalent_organization());
        talent.setUnit(talentEntity.getTalent_unit());
        talent.setHeight(talentEntity.getTalent_height());
        talent.setFanName(talentEntity.getTalent_fan_name());
        return talent;
    }

    // Converts a JsonObject to TalentEntity
    public static TalentEntity toTalentEntity(JsonObject jsonObject) {
        return new TalentEntity(jsonObject);
    }

    // Converts a TalentEntity to JsonObject
    public static JsonObject toJsonObject(TalentEntity talentEntity) {
        return talentEntity.toJsonObject();
    }


    // Converts an Organization object to OrganizationEntity
    public static OrganizationEntity toOrganizationEntity(Organization organization) {
        return new OrganizationEntity(organization);
    }

    // Converts an OrganizationEntity object to an Organization object
    public static Organization toOrganizationModel(OrganizationEntity organizationEntity) {
        Organization organization = new Organization();
        organization.setId(organizationEntity.getId());
        organization.setName(organizationEntity.getName());
        organization.setOwner(organizationEntity.getOwner());
        organization.setWebsite(organizationEntity.getWebsite());
        organization.setFoundedDate(organizationEntity.getFounded());
        organization.setIndustry(organizationEntity.getIndustry());
        organization.setBrands(organizationEntity.getBrands());
        organization.setFounder(organizationEntity.getFounder());
        return organization;
    }

    // Converts a JsonObject to OrganizationEntity
    public static OrganizationEntity toOrganizationEntity(JsonObject jsonObject) {
        return new OrganizationEntity(jsonObject);
    }

    // Converts an OrganizationEntity to JsonObject
    public static JsonObject toJsonObject(OrganizationEntity organizationEntity) {
        return organizationEntity.toJsonObject();
    }
}

package org.betterJavaApplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import org.betterJavaApplication.entity.mead.MeadRecipeEntity;
import org.betterJavaApplication.entity.vtubers.OrganizationEntity;
import org.betterJavaApplication.entity.vtubers.TalentEntity;
import org.object.MeadRecipe;
import org.object.Organization;
import org.object.Talent;

public class EntityToObjectMapper {

    public static ObjectMapper mapper = new ObjectMapper();

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

    public static MeadRecipeEntity toMeadRecipeEntity(MeadRecipe meadRecipe) {
        return new MeadRecipeEntity(meadRecipe);
    }

    public static MeadRecipe toMeadRecipe(MeadRecipeEntity meadRecipeEntity) {
        MeadRecipe recipe = new MeadRecipe();
        recipe.setRecipeId(meadRecipeEntity.getRecipeId());
        recipe.setName(meadRecipeEntity.getName());
        recipe.setBatchSizeGallons(meadRecipeEntity.getBatchSizeGallons());
        recipe.setAbv(meadRecipeEntity.getAbv());
        recipe.setFlavorNotes(meadRecipeEntity.getFlavorNotes());

        try {
            recipe.setIngredients(mapper.readValue(meadRecipeEntity.getIngredients(), new TypeReference<>() {
            }));
            recipe.setSteps(mapper.readValue(meadRecipeEntity.getSteps(), new TypeReference<>() {
            }));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Failed to parse mead recipe JSON for recipe ID " + meadRecipeEntity.getRecipeId(), e);
        }

        return recipe;
    }

    public static JsonObject toJsonObject(MeadRecipeEntity meadRecipeEntity) {
        return meadRecipeEntity.toJSONObject();
    }
}

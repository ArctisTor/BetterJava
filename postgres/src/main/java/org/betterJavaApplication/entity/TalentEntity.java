package org.betterJavaApplication.entity;

import com.google.gson.JsonObject;
import jakarta.persistence.*;
import org.object.Talent;

import java.time.LocalDate;

@Entity
@Table(name="talent")
public class TalentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String talent_id;
    private String talent_name;
    private String talent_debut;
    private String talent_birthday;
    @Column(name="talent_organization")
    private String organization;
    private String talent_unit;
    private Double talent_height;
    private String talent_fan_name;

    protected TalentEntity() {}

    public TalentEntity(String name, String debut, String birthday, String organization, String unit, Double height, String fanName) {
        this.talent_name= name;
        this.talent_debut = debut;
        this.talent_birthday = birthday;
        this.organization = organization;
        this.talent_unit = unit;
        this.talent_height = height;
        this.talent_fan_name = fanName;
    }

    public TalentEntity(Talent talent) {
        this.talent_id = talent.getId();
        this.talent_name = talent.getName();
        this.talent_debut = talent.getDebut();
        this.talent_birthday = talent.getBirthday();
        this.organization = talent.getOrganization();
        this.talent_unit = talent.getUnit();
        this.talent_height = talent.getHeight();
        this.talent_fan_name = talent.getFanName();
    }

    // Constructor to populate fields from JsonObject
    public TalentEntity(JsonObject jsonObject) {
        this.talent_name = jsonObject.get("talent_name").getAsString();
        this.talent_debut = String.valueOf(LocalDate.parse(jsonObject.get("talent_debut").getAsString()));
        this.talent_birthday = String.valueOf(LocalDate.parse(jsonObject.get("talent_birthday").getAsString()));
        this.organization = jsonObject.get("talent_organization").getAsString();
        this.talent_unit = jsonObject.get("talent_unit").getAsString();
        this.talent_height = jsonObject.get("talent_height").getAsDouble();
        this.talent_fan_name = jsonObject.get("talent_fan_name").getAsString();
    }

    public String getTalent_id() {
        return talent_id;
    }

    public void setTalent_id(String talent_id) {
        this.talent_id = talent_id;
    }

    public String getTalent_name() {
        return talent_name;
    }

    public void setTalent_name(String talent_name) {
        this.talent_name = talent_name;
    }

    public String getTalent_debut() {
        return talent_debut;
    }

    public void setTalent_debut(String talent_debut) {
        this.talent_debut = talent_debut;
    }

    public String getTalent_birthday() {
        return talent_birthday;
    }

    public void setTalent_birthday(String talent_birthday) {
        this.talent_birthday = talent_birthday;
    }

    public String getTalent_organization() {
        return organization;
    }

    public void setTalent_organization(String talent_organization) {
        this.organization = talent_organization;
    }

    public String getTalent_unit() {
        return talent_unit;
    }

    public void setTalent_unit(String talent_unit) {
        this.talent_unit = talent_unit;
    }

    public Double getTalent_height() {
        return talent_height;
    }

    public void setTalent_height(Double talent_height) {
        this.talent_height = talent_height;
    }

    public String getTalent_fan_name() {
        return talent_fan_name;
    }

    public void setTalent_fan_name(String talent_fan_name) {
        this.talent_fan_name = talent_fan_name;
    }

    // Method to convert TalentEntity to JsonObject
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("talent_id", this.talent_id);
        jsonObject.addProperty("talent_name", this.talent_name);
        jsonObject.addProperty("talent_debut", this.talent_debut);
        jsonObject.addProperty("talent_birthday", this.talent_birthday);
        jsonObject.addProperty("talent_organization", this.organization);
        jsonObject.addProperty("talent_unit", this.talent_unit);
        jsonObject.addProperty("talent_height", this.talent_height);
        jsonObject.addProperty("talent_fan_name", this.talent_fan_name);
        return jsonObject;
    }
}

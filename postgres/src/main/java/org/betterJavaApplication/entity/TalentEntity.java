package org.betterJavaApplication.entity;

import jakarta.persistence.*;

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
}

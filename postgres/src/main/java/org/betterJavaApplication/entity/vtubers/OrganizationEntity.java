package org.betterJavaApplication.entity.vtubers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.object.Organization;

import java.time.LocalDate;

@Entity
@Table(name = "organization") // Ensure this matches the table name in DB
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use the correct strategy
    private String id;

    private String name;
    private String owner;
    private String website;
    private String founded;
    private String industry;
    private String[] brands;
    private String founder;

    // Getters, setters, constructors
    protected OrganizationEntity() {}

    public OrganizationEntity(String id, String name, String owner, String website, String founded, String industry, String[] brands, String founder) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.website = website;
        this.founded = founded;
        this.industry = industry;
        this.brands = brands;
        this.founder = founder;
    }


    public OrganizationEntity(Organization org) {
        this.id = org.getId();
        this.name = org.getName();
        this.owner = org.getOwner();
        this.website = org.getWebsite();
        this.founded = org.getFoundedDate();
        this.industry = org.getIndustry();
        this.brands = org.getBrands();
        this.founder = org.getFounder();
    }


    public OrganizationEntity(JsonObject jsonObject) {
        this.id = jsonObject.get("id").getAsString();
        this.name = jsonObject.get("name").getAsString();
        this.owner = jsonObject.get("owner").getAsString();
        this.website = jsonObject.get("website").getAsString();
        this.founded = String.valueOf(LocalDate.parse(jsonObject.get("founded").getAsString()));
        this.industry = jsonObject.get("industry").getAsString();

        JsonArray brandsArray = jsonObject.get("brands").getAsJsonArray();
        this.brands = new String[brandsArray.size()];
        for (int i = 0; i < brandsArray.size(); i++) {
            this.brands[i] = brandsArray.get(i).getAsString();
        }
        this.founder = jsonObject.get("founder").getAsString();
    }

    // Method to convert OrganizationEntity to JsonObject
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        jsonObject.addProperty("name", this.name);
        jsonObject.addProperty("owner", this.owner);
        jsonObject.addProperty("website", this.website);
        jsonObject.addProperty("founded", this.founded);
        jsonObject.addProperty("industry", this.industry);

        // Add brands as a JSON array
        JsonArray brandsArray = new JsonArray();
        for (String brand : this.brands) {
            brandsArray.add(brand);
        }
        jsonObject.add("brands", brandsArray);

        jsonObject.addProperty("founder", this.founder);
        return jsonObject;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String[] getBrands() {
        return brands;
    }

    public void setBrands(String[] brands) {
        this.brands = brands;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }
}

package org.betterJavaApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mead_recipes")
public class MeadRecipeEntity {

    @Id
    private Long recipeId;

    private String name;
    private Double batchSizeGallons;
    private Double abv;
    private String flavorNotes;

    // Store as TEXT so it works with all DBs
    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @Column(columnDefinition = "TEXT")
    private String steps;

    // Getters and setters
    public Long getRecipeId() { return recipeId; }
    public void setRecipeId(Long recipeId) { this.recipeId = recipeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getBatchSizeGallons() { return batchSizeGallons; }
    public void setBatchSizeGallons(Double batchSizeGallons) { this.batchSizeGallons = batchSizeGallons; }

    public Double getAbv() { return abv; }
    public void setAbv(Double abv) { this.abv = abv; }

    public String getFlavorNotes() { return flavorNotes; }
    public void setFlavorNotes(String flavorNotes) { this.flavorNotes = flavorNotes; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
}

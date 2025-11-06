package org.betterJavaApplication.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.object.MeadRecipe;

import java.util.List;

@Entity
@Table(name = "mead_recipes")
public class MeadRecipeEntity {


    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "recipe_id", updatable = false, nullable = false)
    private String recipeId;

    private String name;
    private Double batchSizeGallons;
    private Double abv;
    private String flavorNotes;

    @Column(columnDefinition = "text")
    private String ingredients;

    @Column(columnDefinition = "text")
    private String steps;

    protected  MeadRecipeEntity() {}

    public MeadRecipeEntity(MeadRecipe meadRecipe) {
        this.recipeId = meadRecipe.getRecipeId();
        this.name = meadRecipe.getName();
        this.batchSizeGallons = meadRecipe.getBatchSizeGallons();
        this.abv = meadRecipe.getAbv();
        this.flavorNotes = meadRecipe.getFlavorNotes();

        ObjectMapper mapper = new ObjectMapper();
        try {
            Object ingredientsObj = meadRecipe.get("ingredients");
            Object stepsObj = meadRecipe.get("steps");

            this.ingredients = mapper.writeValueAsString(ingredientsObj != null ? ingredientsObj : List.of());
            this.steps = mapper.writeValueAsString(stepsObj != null ? stepsObj : List.of());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    // Getters and setters
    public String getRecipeId() { return recipeId; }
    public void setRecipeId(String recipeId) { this.recipeId = recipeId; }

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

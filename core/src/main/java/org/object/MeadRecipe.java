package org.object;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public class MeadRecipe extends HashMap<String, Object> {

    private static final ObjectMapper mapper = new ObjectMapper();

    public MeadRecipe() {
        super();
    }

    public void setRecipeId(Long recipeId) {
        this.put("recipe_id", recipeId);
    }

    public Long getRecipeId() {
        Object value = this.get("recipe_id");
        if (value instanceof Number) return ((Number) value).longValue();
        return null;
    }


    // Convenience setters
    public void setName(String name) {
        this.put("name", name);
    }

    public void setBatchSizeGallons(Double batchSize) {
        this.put("batch_size_gallons", batchSize);
    }

    public void setAbv(Double abv) {
        this.put("abv", abv);
    }

    public void setFlavorNotes(String notes) {
        this.put("flavor_notes", notes);
    }

    public void setIngredients(List<MeadIngredients> ingredients) {
        this.put("ingredients", ingredients);
    }

    public void setSteps(List<MeadSteps> steps) {
        this.put("steps", steps);
    }


    // Convenience getters
    public String getName() {
        return (String) this.get("name");
    }

    public Double getBatchSizeGallons() {
        return (Double) this.get("batch_size_gallons");
    }

    public Double getAbv() {
        return (Double) this.get("abv");
    }

    public String getFlavorNotes() {
        return (String) this.get("flavor_notes");
    }

    public JsonNode getIngredients() {
        Object obj = this.get("ingredients");
        if (obj instanceof JsonNode) return (JsonNode) obj;
        else if (obj instanceof String) {
            try { return mapper.readTree((String) obj); }
            catch (Exception e) { e.printStackTrace(); return null; }
        }
        return null;
    }

    public JsonNode getSteps() {
        Object obj = this.get("steps");
        if (obj instanceof JsonNode) return (JsonNode) obj;
        else if (obj instanceof String) {
            try { return mapper.readTree((String) obj); }
            catch (Exception e) { e.printStackTrace(); return null; }
        }
        return null;
    }

    public void addIngredientSection(String sectionName, List<String> items) {
        JsonNode ingredientsNode = this.getIngredients();
        try {
            if (ingredientsNode == null || ingredientsNode.isNull()) {
                ingredientsNode = mapper.createArrayNode();
            }
            ((com.fasterxml.jackson.databind.node.ArrayNode) ingredientsNode).add(
                    mapper.createObjectNode()
                            .put("section", sectionName)
                            .set("items", mapper.valueToTree(items))
            );
            this.put("ingredients", ingredientsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStepSection(String title, List<String> steps) {
        JsonNode stepsNode = this.getSteps();
        try {
            if (stepsNode == null || stepsNode.isNull()) {
                stepsNode = mapper.createArrayNode();
            }
            ((com.fasterxml.jackson.databind.node.ArrayNode) stepsNode).add(
                    mapper.createObjectNode()
                            .put("title", title)
                            .set("steps", mapper.valueToTree(steps))
            );
            this.put("steps", stepsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


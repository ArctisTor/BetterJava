package org.object;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class MeadRecipe extends HashMap<String, Object> {

    public MeadRecipe() {
        super();
    }

    public void setRecipeId(String recipeId) {
        this.put("recipe_id", recipeId);
    }

    public String getRecipeId() {
        return (String) this.get("recipe_id");
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

    public List<MeadIngredients> getIngredients() {
        Object value = this.get("ingredients");
        if (value instanceof List<?> list) {
            Gson gson = new Gson();

            // Convert LinkedTreeMap to MeadIngredients
            return list.stream()
                    .map(item -> {
                        if (item instanceof MeadIngredients mi) {
                            return mi; // already correct type
                        } else if (item instanceof java.util.Map) {
                            // Convert LinkedTreeMap -> JSON -> MeadIngredients
                            String json = gson.toJson(item);
                            return gson.fromJson(json, MeadIngredients.class);
                        } else {
                            return null; // unexpected type
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void setSteps(List<MeadSteps> steps) {
        this.put("steps", steps);
    }

    public List<MeadSteps> getSteps() {
        Object value = this.get("steps");
        if (value instanceof List<?> list) {
            Gson gson = new Gson();

            // Convert LinkedTreeMap -> MeadSteps
            return list.stream()
                    .map(item -> {
                        if (item instanceof MeadSteps step) {
                            return step; // already correct type
                        } else if (item instanceof Map<?, ?> map) {
                            // Convert LinkedTreeMap -> JSON -> MeadSteps
                            String json = gson.toJson(map);
                            return gson.fromJson(json, MeadSteps.class);
                        } else {
                            return null; // unexpected type
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
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
}

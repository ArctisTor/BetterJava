package org.validator;

import com.fasterxml.jackson.databind.JsonNode;
import org.object.MeadRecipe;

public class MeadRecipeValidator {

    /**
     * Validates a Mead_Recipe instance.
     * @param recipe the Mead_Recipe object to validate
     * @return true if valid, false otherwise
     */
    public boolean validate(MeadRecipe recipe) {
        if (recipe == null) {
            System.out.println("Recipe is null.");
            return false;
        }

        // Validate basic fields
        if (recipe.getName() == null || recipe.getName().trim().isEmpty()) {
            System.out.println("Recipe name is missing.");
            return false;
        }

        if (recipe.getBatchSizeGallons() == null || recipe.getBatchSizeGallons() <= 0) {
            System.out.println("Batch size must be greater than 0.");
            return false;
        }

        if (recipe.getAbv() != null && (recipe.getAbv() < 0 || recipe.getAbv() > 100)) {
            System.out.println("ABV must be between 0 and 100.");
            return false;
        }

        // Validate JSON fields
        if (!validateIngredients(recipe.getIngredients())) {
            System.out.println("Ingredients are invalid.");
            return false;
        }

        if (!validateSteps(recipe.getSteps())) {
            System.out.println("Steps are invalid.");
            return false;
        }

        return true;
    }

    /**
     * Validates the ingredients JSON structure.
     * Expects an array of objects with "section" and "items" fields.
     */
    private boolean validateIngredients(JsonNode ingredients) {
        if (ingredients == null || !ingredients.isArray() || ingredients.isEmpty()) {
            System.out.println("Ingredients JSON must be a non-empty array.");
            return false;
        }

        for (JsonNode section : ingredients) {
            if (!section.has("section") || !section.has("items")) {
                System.out.println("Each ingredient section must have 'section' and 'items'.");
                return false;
            }
            if (!section.get("items").isArray() || section.get("items").isEmpty()) {
                System.out.println("Ingredient items must be a non-empty array.");
                return false;
            }
        }

        return true;
    }

    /**
     * Validates the steps JSON structure.
     * Expects an array of objects with "title" and "steps" fields.
     */
    private boolean validateSteps(JsonNode steps) {
        if (steps == null || !steps.isArray() || steps.isEmpty()) {
            System.out.println("Steps JSON must be a non-empty array.");
            return false;
        }

        for (JsonNode stepSection : steps) {
            if (!stepSection.has("title") || !stepSection.has("steps")) {
                System.out.println("Each step section must have 'title' and 'steps'.");
                return false;
            }
            if (!stepSection.get("steps").isArray() || stepSection.get("steps").isEmpty()) {
                System.out.println("Steps array in a section must not be empty.");
                return false;
            }
        }

        return true;
    }
}


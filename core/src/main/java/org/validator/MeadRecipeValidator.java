package org.validator;

import org.object.MeadIngredients;
import org.object.MeadRecipe;
import org.object.MeadSteps;

import java.util.List;

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
    private boolean validateIngredients(List<MeadIngredients> ingredients) {
        if (ingredients.isEmpty()) {
            System.out.println("Ingredients must be a non-empty array.");
            return false;
        }

        for (MeadIngredients ingredient: ingredients) {
            // Check if section is null or blank
            if (ingredient.getSection() == null || ingredient.getSection().isBlank()) {
                return false;
            }

            // Check if items list is null or empty
            List<String> items = ingredient.getItems();
            if (items == null || items.isEmpty()) {
                return false;
            }

            // Check if any item is null or blank
            if (items.stream().anyMatch(s -> s == null || s.isBlank())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates the steps JSON structure.
     * Expects an array of objects with "title" and "steps" fields.
     */
    private boolean validateSteps(List<MeadSteps> steps) {
        if (steps.isEmpty()) {
            System.out.println("Steps must be a non-empty array.");
            return false;
        }

        for (MeadSteps meadSteps: steps) {
            // Check if Title is null or blank
            if (meadSteps.getTitle() == null || meadSteps.getTitle().isBlank()) {
                return false;
            }

            // Check if steps list is null or empty
            List<String> steps1 = meadSteps.getSteps();
            if (steps1 == null || steps1.isEmpty()) {
                return false;
            }

            // Check if any item is null or blank
            if (steps1.stream().anyMatch(s -> s == null || s.isBlank())) {
                return false;
            }
        }

        return true;
    }
}


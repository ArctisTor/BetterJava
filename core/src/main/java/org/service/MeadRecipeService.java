package org.service;

import org.object.MeadRecipe;

import java.util.List;

public interface MeadRecipeService {

    List<MeadRecipe> getAllMeadRecipes();
    boolean updateMeadRecipe(MeadRecipe meadRecipe);
}

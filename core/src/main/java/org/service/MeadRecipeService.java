package org.service;

import com.google.gson.JsonObject;
import org.object.MeadRecipe;

import java.util.List;
import java.util.Optional;

public interface MeadRecipeService {

    List<MeadRecipe> getAllMeadRecipes(int limit, int offset);

    boolean updateMeadRecipe(MeadRecipe meadRecipe);

    JsonObject addMeadRecipe(MeadRecipe meadRecipe);

    Optional<MeadRecipe> deleteMeadRecipe(String id);
}

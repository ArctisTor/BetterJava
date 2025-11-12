package org.service;

import com.google.gson.JsonObject;
import org.object.MeadRecipe;

import java.util.List;

public interface MeadRecipeService {

    List<MeadRecipe> getAllMeadRecipes(int limit, int offset);
    boolean updateMeadRecipe(MeadRecipe meadRecipe);
    JsonObject addMeadRecipe(MeadRecipe meadRecipe);
}

package org.betterJavaApplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.betterJavaApplication.connector.PostgresConnector;
import org.betterJavaApplication.entity.MeadRecipeEntity;
import org.betterJavaApplication.repository.MeadRecipeRepository;
import org.object.MeadIngredients;
import org.object.MeadRecipe;
import org.object.MeadSteps;
import org.service.MeadRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.validator.MeadRecipeValidator;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostgresMeadRecipeService implements MeadRecipeService {

    private final PostgresConnector postgresConnector;
    private final MeadRecipeRepository meadRecipeRepository;
    private final Gson gson = new Gson();
    ObjectMapper mapper = new ObjectMapper();
    private final MeadRecipeValidator meadRecipeValidator = new MeadRecipeValidator();

    @Autowired
    public PostgresMeadRecipeService(PostgresConnector postgresConnector, MeadRecipeRepository meadRecipeRepository) {
        this.postgresConnector = postgresConnector;
        this.meadRecipeRepository = meadRecipeRepository;
    }

    @PostConstruct
    public void connect() {
        this.postgresConnector.connectToPostgres();
    }

    @PreDestroy
    public void cleanup() {
        this.postgresConnector.closeConnection();
    }

    @Override
    public List<MeadRecipe> getAllMeadRecipes()  {
        List<MeadRecipe> meadRecipeList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (MeadRecipeEntity entity : meadRecipeRepository.findAll()) {
            MeadRecipe recipe = new MeadRecipe();
            recipe.setRecipeId(entity.getRecipeId());
            recipe.setName(entity.getName());
            recipe.setBatchSizeGallons(entity.getBatchSizeGallons());
            recipe.setAbv(entity.getAbv());
            recipe.setFlavorNotes(entity.getFlavorNotes());

            List<MeadIngredients> ingredients;
            List<MeadSteps> steps;

            try {
                ingredients = mapper.readValue(
                        entity.getIngredients(),
                        new TypeReference<>() {
                        }
                );

                steps = mapper.readValue(
                        entity.getSteps(),
                        new TypeReference<>() {
                        }
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse mead recipe JSON for recipe ID " + entity.getRecipeId(), e);
            }

            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);



            meadRecipeList.add(recipe);
        }

        return meadRecipeList;
    }



}

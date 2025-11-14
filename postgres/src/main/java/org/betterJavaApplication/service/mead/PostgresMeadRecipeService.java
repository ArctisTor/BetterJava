package org.betterJavaApplication.service.mead;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.betterJavaApplication.entity.mead.MeadRecipeEntity;
import org.betterJavaApplication.repository.mead.MeadRecipeRepository;
import org.betterJavaApplication.utils.EntityToObjectMapper;
import org.object.MeadRecipe;
import org.service.mead.MeadRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.validator.MeadRecipeValidator;

import java.util.List;
import java.util.Optional;

@Service
public class PostgresMeadRecipeService implements MeadRecipeService {

    private final MeadRecipeRepository meadRecipeRepository;
    ObjectMapper mapper = new ObjectMapper();
    private final MeadRecipeValidator meadRecipeValidator = new MeadRecipeValidator();

    @PersistenceContext(unitName = "meadPU")
    private EntityManager entityManager;

    @Autowired
    public PostgresMeadRecipeService(MeadRecipeRepository meadRecipeRepository) {
        this.meadRecipeRepository = meadRecipeRepository;
    }

    @Override
    public List<MeadRecipe> getAllMeadRecipes(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        List<MeadRecipeEntity> entities = meadRecipeRepository.findAll(pageable).getContent();

        return entities.stream().map(entity -> {
            MeadRecipe recipe = EntityToObjectMapper.toMeadRecipe(entity);
            return recipe;
        }).toList();
    }

    @Override
    public List<MeadRecipe> getMeadRecipesByName(String name) {
        List<MeadRecipeEntity> entities = meadRecipeRepository.findByNameContainingIgnoreCase(name);
        return entities.stream().map(entity -> {
            MeadRecipe recipe = EntityToObjectMapper.toMeadRecipe(entity);
            return recipe;
        }).toList();
    }

    @Override
    public boolean updateMeadRecipe(MeadRecipe meadRecipe) {
        Optional<MeadRecipeEntity> existingMeadRecipe = meadRecipeRepository.findById(meadRecipe.getRecipeId());
        if (existingMeadRecipe.isPresent()) {
            MeadRecipeEntity convertedRecipe = new MeadRecipeEntity(meadRecipe);
            MeadRecipeEntity saved = meadRecipeRepository.save(convertedRecipe);
            return saved.getRecipeId() != null;
        }
        return false;
    }

    @Override
    public JsonObject addMeadRecipe(MeadRecipe meadRecipe) {
        JsonObject response = new JsonObject();
        if (!meadRecipeValidator.validate(meadRecipe)) {
            response.addProperty("error", String.format("%s is not a valid mead recipe.", meadRecipe.getName()));
            return response;
        }

        // Checks to see if it already exists
        if (meadRecipe.getRecipeId() != null && !meadRecipe.getRecipeId().isBlank()) {
            String id = meadRecipe.getRecipeId();
            Optional<MeadRecipeEntity> existingMeadRecipe = meadRecipeRepository.findById(id);
            if (existingMeadRecipe.isPresent()) {
                response.addProperty(
                        "error",
                        String.format("There was an error creating mead recipe: %s already exists",
                                meadRecipe.getName()));
                return response;
            }
        }

        MeadRecipeEntity convertedRecipe = new MeadRecipeEntity(meadRecipe);
        MeadRecipeEntity saved = meadRecipeRepository.save(convertedRecipe);
        if (!saved.getRecipeId().isBlank()) {
            response.addProperty(
                    "success",
                    String.format("Successfully created mead recipe %s for %s", saved.getRecipeId(), saved.getName()));
        } else {
            response.addProperty("error",
                    String.format("There was an error creating mead recipe %s", convertedRecipe.getName()));
        }
        return response;
    }

    @Override
    public Optional<MeadRecipe> deleteMeadRecipe(String id) {
        Optional<MeadRecipeEntity> foundMeadRecipe = meadRecipeRepository.findById(id);
        if (!foundMeadRecipe.isPresent()) {
            return Optional.empty();
        }
        meadRecipeRepository.delete(foundMeadRecipe.get());
        MeadRecipe recipe = EntityToObjectMapper.toMeadRecipe(foundMeadRecipe.get());
        return Optional.of(recipe);
    }

}

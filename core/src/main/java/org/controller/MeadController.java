package org.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.object.MeadRecipe;
import org.service.MeadRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/meads")
public class MeadController {

    private final Gson gson = new Gson();
    @Autowired
    private MeadRecipeService meadRecipeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getAllMeadRecipes(
            @RequestParam(name = "limit", required = false, defaultValue = "2147483647") int limit,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {
        JsonObject response = new JsonObject();
        JsonArray meadArray = new JsonArray();
        List<MeadRecipe> meadRecipeList = this.meadRecipeService.getAllMeadRecipes(limit, offset);
        meadRecipeList.forEach(meadRecipe -> meadArray.add(gson.toJsonTree(meadRecipe)));
        response.add("Meads", meadArray);
        response.addProperty("limit", limit);
        response.addProperty("offset", offset);
        response.addProperty("count", meadRecipeList.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JsonObject> addMeadRecipe(@RequestBody MeadRecipe newMead) {
        JsonObject response;
        response = this.meadRecipeService.addMeadRecipe(newMead);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<JsonObject> updateMeadRecipe(@RequestBody MeadRecipe updateMead) {
        JsonObject response = new JsonObject();
        if (updateMead.getRecipeId() == null || updateMead.getRecipeId().isBlank()) {
            response.addProperty("error", "ID is empty");
            return ResponseEntity.badRequest().body(response);
        }

        boolean updated = this.meadRecipeService.updateMeadRecipe(updateMead);
        if (updated) {
            response.addProperty("success", String.format("Updated mead: %s", updateMead.getRecipeId()));
        } else {
            response.addProperty("error", String.format("Could not update mead: %s", updateMead.getRecipeId()));
        }
        return updated ? new ResponseEntity<>(response, HttpStatus.OK) : ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> deleteMeadRecipe(@PathVariable("id") String id) {
        JsonObject response = new JsonObject();
        if (id == null || id.isBlank()) {
            response.addProperty("error", "ID is empty");
            return ResponseEntity.badRequest().body(response);
        }
        Optional<MeadRecipe> deletedMeadRecipeOptional = meadRecipeService.deleteMeadRecipe(id);
        if (deletedMeadRecipeOptional.isPresent()) {
            response.addProperty("success", String.format("Deleted mead recipe with id: %s", id));
            JsonObject meadJsonObject = gson.toJsonTree(deletedMeadRecipeOptional.get()).getAsJsonObject();
            response.add("deleted_mead_recipe", meadJsonObject);
        } else {
            response.addProperty("error", String.format("Could not delete mead with id: %s", id));
        }

        return deletedMeadRecipeOptional.isPresent() ? new ResponseEntity<>(response, HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

package org.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.object.MeadRecipe;
import org.service.MeadRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Mead")
public class MeadController {

    private final Gson gson = new Gson();
    @Autowired
    private MeadRecipeService meadRecipeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getAllMeadRecipes() {
        JsonObject response = new JsonObject();
        JsonArray meadArray = new JsonArray();
        List<MeadRecipe> meadRecipeList = this.meadRecipeService.getAllMeadRecipes();
        meadRecipeList.forEach(meadRecipe -> meadArray.add(gson.toJsonTree(meadRecipe)));
        response.add("Meads", meadArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JsonObject> addMeadRecipe(@RequestBody MeadRecipe newMead) {
        JsonObject response = new JsonObject();
        response = this.meadRecipeService.addMeadRecipe(newMead);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<JsonObject> updateMeadRecipe(@RequestBody MeadRecipe updateMead) {
        JsonObject response = new JsonObject();
        if (updateMead.getRecipeId() == null) {
            response.addProperty("error", "ID is empty");
            return ResponseEntity.badRequest().body(response);
        }

        if (this.meadRecipeService.updateMeadRecipe(updateMead)) {
            response.addProperty("success", String.format("Updated mead: %s", updateMead.getRecipeId()));
        } else {
            response.addProperty("error", String.format("Could not update mead: %s", updateMead.getRecipeId()));
        }
        return ResponseEntity.badRequest().body(response);
    }

}

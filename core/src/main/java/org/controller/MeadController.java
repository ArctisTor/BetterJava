package org.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.object.MeadRecipe;
import org.object.Talent;
import org.service.MeadRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        meadRecipeList.forEach(meadRecipe -> {
            meadArray.add(gson.toJsonTree(meadRecipe));
        });
        response.add("Meads", meadArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

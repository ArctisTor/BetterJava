package org.betterJavaApplication.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.betterJavaApplication.service.PostgresVTuberService;
import org.object.Talent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Vtuber")
public class VTuberController {

    @Autowired
    private PostgresVTuberService postgresVTuberService;


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getAllVTubers() {
        JsonObject response = new JsonObject();
        JsonArray vtuberArray = new JsonArray();
        Gson gson = new Gson();
        List<Talent> vtuberList = this.postgresVTuberService.getAllVTubers();
        vtuberList.forEach(vtuber -> {
           vtuberArray.add(gson.toJsonTree(vtuber));
        });
        response.add("Vtubers", vtuberArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package org.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.object.Talent;
import org.service.VTuberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vtuber")
public class VTuberController {

    @Autowired
    private VTuberService vTuberService;
    private final Gson gson = new Gson();


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getAllVTubers() {
        JsonObject response = new JsonObject();
        JsonArray vtuberArray = new JsonArray();
        List<Talent> vtuberList = this.vTuberService.getAllVTubers();
        vtuberList.forEach(vtuber -> {
           vtuberArray.add(gson.toJsonTree(vtuber));
        });
        response.add("Vtubers", vtuberArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/smol", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getTheSmolVTuber() {
        JsonObject response = new JsonObject();
        Talent smolTuber = this.vTuberService.getShortestTalent();
        response.add("SmolTuber", gson.toJsonTree(smolTuber));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/{orgName}")
    public ResponseEntity<JsonObject> getByOrganization(@PathVariable("orgName") String orgName) {
        JsonObject response = new JsonObject();
        JsonArray vtuberArray = new JsonArray();
        List<Talent> talentEntityList = this.vTuberService.getByOrganization(orgName);
        talentEntityList.forEach(vtuber -> {
            vtuberArray.add(gson.toJsonTree(vtuber));
        });
        response.add("VTubers", vtuberArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/debut")
    public ResponseEntity<JsonObject> debutTalent(@RequestBody Talent debutTalent) {
        Talent debuted = this.vTuberService.debutVTuberTalent(debutTalent);
        JsonObject response = debuted.toJsonObject();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

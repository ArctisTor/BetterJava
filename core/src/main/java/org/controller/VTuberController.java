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

    private final Gson gson = new Gson();
    @Autowired
    private VTuberService vTuberService;

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

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getVTuberTalentById(@RequestParam("id") String id) {
        JsonObject response = new JsonObject();
        if (id == null || id.isBlank()) {
            response.addProperty("error", "ID is empty");
            return ResponseEntity.badRequest().body(response);
        }
        Talent foundTalent = vTuberService.getVTuberTalentById(id);
        if (foundTalent != null) {
            response = foundTalent.toJsonObject();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.addProperty("error", String.format("Could not find Talent with id %s", id));
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getVTuberTalentByName(@RequestParam("name") String name) {
        JsonObject response = new JsonObject();
        if (name == null || name.isBlank()) {
            response.addProperty("error", "Name is empty");
            return ResponseEntity.badRequest().body(response);
        }

        JsonArray vTuberArray = new JsonArray();
        List<Talent> foundTalents = vTuberService.getVTuberTalentByName(name);
        foundTalents.forEach(talent -> {
            vTuberArray.add(gson.toJsonTree(talent));
        });
        response.add("VTubers", vTuberArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/smol", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getTheSmolVTuber() {
        JsonObject response = new JsonObject();
        Talent smolTuber = this.vTuberService.getShortestTalent();
        response.add("SmolTuber", gson.toJsonTree(smolTuber));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/org", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getByOrganization(@RequestParam("org") String orgName) {
        JsonObject response = new JsonObject();
        JsonArray vTuberArray = new JsonArray();
        List<Talent> talentEntityList = this.vTuberService.getByOrganization(orgName);
        talentEntityList.forEach(vTuber -> {
            vTuberArray.add(gson.toJsonTree(vTuber));
        });
        response.add("VTubers", vTuberArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/debut")
    public ResponseEntity<JsonObject> debutTalent(@RequestBody Talent debutTalent) {
        try {
            Talent debuted = this.vTuberService.debutVTuberTalent(debutTalent);
            JsonObject response = debuted.toJsonObject();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (RuntimeException e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping
    public ResponseEntity<JsonObject> updateTalent(@RequestBody Talent updateTalent) {
        try {
            Talent successfullyUpdated = this.vTuberService.updateVTuberTalent(updateTalent);
            JsonObject response = successfullyUpdated.toJsonObject();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (RuntimeException e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

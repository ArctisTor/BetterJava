package org.controller;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.service.ConfigurationInfoService;
import org.service.DatabaseTestConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/heartbeat")
public class HeartBeatController {

    @Autowired
    private ConfigurationInfoService configurationInfoService;
    @Autowired
    private DatabaseTestConnectionService databaseTestConnectionService;
    private final Gson gson = new Gson();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getHeartBeat() {
        JsonObject response = new JsonObject();

        //Version Info
        response.add("version-number", gson.toJsonTree(configurationInfoService.getVersionNumber()));
        //Database Test
        response.add("database-info", databaseTestConnectionService.testConnection());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

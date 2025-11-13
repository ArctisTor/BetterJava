package org.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.object.Organization;
import org.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    private final Gson gson = new Gson();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getAllOrganizations(
            @RequestParam(name = "limit", required = false, defaultValue = "2147483647") int limit,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {
        JsonObject response = new JsonObject();
        JsonArray orgArray = new JsonArray();
        List<Organization> orgList = organizationService.getAllOrganizations(limit, offset);
        orgList.forEach(organization -> {
            orgArray.add(gson.toJsonTree(organization));
        });
        response.add("Organizations", orgArray);
        response.addProperty("limit", limit);
        response.addProperty("offset", offset);
        response.addProperty("count", orgArray.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{orgName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonObject> getByOrganization(@PathVariable("orgName") String orgName) {
        JsonObject response = new JsonObject();
        try {
            Organization org = organizationService.getOrganizationByName(orgName);
            response.add("Organization", gson.toJsonTree(org));
        } catch (Exception ignored) {

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

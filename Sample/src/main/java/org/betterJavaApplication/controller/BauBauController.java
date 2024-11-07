package org.betterJavaApplication.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baubau")
public class BauBauController {

    @GetMapping("")
    public ResponseEntity<JSONObject> sayBauBau(@RequestParam String name) {
        JSONObject response = new JSONObject();
        response.put("Bau Bau", name);
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatusCode.valueOf(200));
        return responseEntity;
    }
}

package org.betterJavaApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Greeting")
public class GreetingController {

    @GetMapping("")
    public String greeting() {
        return "Hello!";
    }
}

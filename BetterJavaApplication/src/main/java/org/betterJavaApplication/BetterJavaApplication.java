package org.betterJavaApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.betterJavaApplication", "org.controller", "org.object", "org.service"})
public class BetterJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetterJavaApplication.class, args);
    }
}

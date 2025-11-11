package org.betterJavaApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = {"org.betterJavaApplication", "org.controller", "org.object", "org.service"},
        exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
)
public class BetterJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetterJavaApplication.class, args);
    }
}

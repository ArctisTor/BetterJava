package org.betterJavaApplication.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaCommonConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties commonJpaProperties() {
        return new JpaProperties();
    }
}

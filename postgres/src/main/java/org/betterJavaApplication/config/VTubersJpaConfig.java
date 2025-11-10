package org.betterJavaApplication.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.betterJavaApplication.repository.vtubers",
        entityManagerFactoryRef = "vtubersEntityManagerFactory",
        transactionManagerRef = "vtubersTransactionManager"
)
public class VTubersJpaConfig {

    // ---------------------------
    // VTUBERS DataSource
    // ---------------------------
    @Primary
    @Bean(name = "vtubersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.vtubers")
    public HikariDataSource vtubersDataSource() {
        return new HikariDataSource();
    }

    // ---------------------------
    // VTUBERS JPA Properties
    // ---------------------------
    @Primary
    @Bean(name = "vtubersJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.vtubers")
    public JpaProperties vtubersJpaProperties() {
        return new JpaProperties();
    }

    // ---------------------------
    // VTUBERS EntityManagerFactory
    // ---------------------------
    @Primary
    @Bean(name = "vtubersEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean vtubersEntityManagerFactory(
            @Qualifier("vtubersDataSource") DataSource dataSource,
            @Qualifier("commonJpaProperties") JpaProperties jpaProperties
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.betterJavaApplication.entity.vtubers");
        emf.setPersistenceUnitName("vtubersPU");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Copy properties from JpaProperties
        Map<String, Object> props = new HashMap<>(jpaProperties.getProperties());

        // Ensure dialect is picked up from databasePlatform if not already present
        if (!props.containsKey("hibernate.dialect") && jpaProperties.getDatabasePlatform() != null) {
            props.put("hibernate.dialect", jpaProperties.getDatabasePlatform());
        }

        emf.setJpaPropertyMap(props); // now respects spring.jpa.vtubers.* properties
        return emf;
    }


    // ---------------------------
    // VTUBERS TransactionManager
    // ---------------------------
    @Primary
    @Bean(name = "vtubersTransactionManager")
    public PlatformTransactionManager vtubersTransactionManager(
            @Qualifier("vtubersEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}

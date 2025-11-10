package org.betterJavaApplication.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "org.betterJavaApplication.repository.mead",
        entityManagerFactoryRef = "meadEntityManagerFactory",
        transactionManagerRef = "meadTransactionManager"
)
public class MeadJpaConfig {

    // ---------------------------
    // MEAD DataSource
    // ---------------------------
    @Bean(name = "meadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mead")
    public DataSource meadDataSource() {
        return new HikariDataSource();
    }

    // ---------------------------
    // MEAD EntityManagerFactory
    // ---------------------------
    @Bean(name = "meadEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean meadEntityManagerFactory(
            @Qualifier("meadDataSource") DataSource dataSource,
            @Qualifier("commonJpaProperties") JpaProperties jpaProperties
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.betterJavaApplication.entity.mead");
        emf.setPersistenceUnitName("meadPU");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Copy properties from JpaProperties
        Map<String, Object> props = new HashMap<>(jpaProperties.getProperties());

        // Ensure dialect is picked up from databasePlatform
        if (!props.containsKey("hibernate.dialect") && jpaProperties.getDatabasePlatform() != null) {
            props.put("hibernate.dialect", jpaProperties.getDatabasePlatform());
        }

        emf.setJpaPropertyMap(props);
        return emf;
    }

    // ---------------------------
    // MEAD TransactionManager
    // ---------------------------
    @Bean(name = "meadTransactionManager")
    public PlatformTransactionManager meadTransactionManager(
            @Qualifier("meadEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}

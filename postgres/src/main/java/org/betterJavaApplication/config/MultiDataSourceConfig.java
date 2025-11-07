package org.betterJavaApplication.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;

@Configuration
public class MultiDataSourceConfig {

    // ---------------------------
    // VTUBERS
    // ---------------------------
    @Bean(name = "vtubersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.vtubers")
    public HikariDataSource vtubersDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "vtubersEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean vtubersEntityManagerFactory(
            @Qualifier("vtubersDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.betterJavaApplication.entity.vtubers");
        emf.setPersistenceUnitName("vtubersPU");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", false);
        emf.setJpaPropertyMap(props);

        return emf;
    }

    @Bean(name = "vtubersTransactionManager")
    public PlatformTransactionManager vtubersTransactionManager(
            @Qualifier("vtubersEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }

    // ---------------------------
    // MEAD
    // ---------------------------
    @Bean(name = "meadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mead")
    public HikariDataSource meadDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "meadEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean meadEntityManagerFactory(
            @Qualifier("meadDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.betterJavaApplication.entity.mead");
        emf.setPersistenceUnitName("meadPU");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", false);
        emf.setJpaPropertyMap(props);

        return emf;
    }

    @Bean(name = "meadTransactionManager")
    public PlatformTransactionManager meadTransactionManager(
            @Qualifier("meadEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}

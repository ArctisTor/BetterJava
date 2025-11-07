package org.betterJavaApplication.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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

@Configuration
@EnableJpaRepositories(
        basePackages = "org.betterJavaApplication.repository.mead",
        entityManagerFactoryRef = "meadEntityManagerFactory",
        transactionManagerRef = "meadTransactionManager"
)
public class MeadJpaConfig {

    // Load Mead datasource properties
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.mead")
    public DataSourceProperties meadDataSourceProperties() {
        return new DataSourceProperties();
    }

    // Build the HikariDataSource from properties
    @Bean(name = "meadDataSource")
    @ConfigurationProperties("spring.datasource.mead.hikari")
    public HikariDataSource meadDataSource(@Qualifier("meadDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    // EntityManagerFactory
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

    // TransactionManager
    @Bean(name = "meadTransactionManager")
    public PlatformTransactionManager meadTransactionManager(
            @Qualifier("meadEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}

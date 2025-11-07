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
        basePackages = "org.betterJavaApplication.repository.vtubers",
        entityManagerFactoryRef = "vtubersEntityManagerFactory",
        transactionManagerRef = "vtubersTransactionManager"
)
public class VTubersJpaConfig {

    // Load the properties first
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties vtubersDataSourceProperties() {
        return new DataSourceProperties();
    }

    // Build the HikariDataSource from the properties
    @Primary
    @Bean(name = "vtubersDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource vtubersDataSource(@Qualifier("vtubersDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
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
}

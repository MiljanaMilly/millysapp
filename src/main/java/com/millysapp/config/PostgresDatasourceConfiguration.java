package com.millysapp.config;

import com.millysapp.model.Skunk;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application-local.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityPgManagerFactory",
        transactionManagerRef = "transactionPGManager",
        basePackages = "com.millysapp.repository.postgres"
)
public class PostgresDatasourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasources.postgresql")
    public DataSourceProperties dataSourcePropertiesPg() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "datasources.postgresql.configuration")
    public HikariDataSource dataSourcePg() {
        return dataSourcePropertiesPg().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "entityPgManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("pgDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages(Skunk.class)
                .persistenceUnit("postgresqlPU")
                .build();
    }

    @Primary
    @Bean(name = "transactionPGManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityPgManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializerPg(@Qualifier("pgDataSource") DataSource datasource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("db/skunksPg.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}

package com.millysapp.config;

import com.millysapp.model.Skunk;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "entityMdbManagerFactory",
        transactionManagerRef = "transactionMdbManager",
        basePackages = "com.millysapp.repository.mariadb"
)
public class MariaDBDatasourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasources.mariadb")
    public DataSourceProperties dataSourcePropertiesMdb() {
        return new DataSourceProperties();
    }

    @Bean(name = "mdbDataSource")
    @ConfigurationProperties(prefix = "datasources.mariadb.configuration")
    public HikariDataSource dataSourcePg() {
        return dataSourcePropertiesMdb().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "entityMdbManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("mdbDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages(Skunk.class)
                .persistenceUnit("mariadbPU")
                .build();
    }

    @Bean(name = "transactionMdbManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityMdbManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializerMdb(@Qualifier("mdbDataSource") DataSource datasource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("db/skunksMdb.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}

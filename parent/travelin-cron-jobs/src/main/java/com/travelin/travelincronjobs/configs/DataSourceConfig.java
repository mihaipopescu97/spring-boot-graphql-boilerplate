package com.travelin.travelincronjobs.configs;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/29/2022, Thu
 **/

@Log4j2
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        log.info("Creating new data source.");
        final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(environment.getProperty("JDBC_URL"));
        dataSourceBuilder.username(environment.getProperty("DB_USERNAME"));
        dataSourceBuilder.password(environment.getProperty("DB_PASSWORD"));
        final DataSource dataSource = dataSourceBuilder.build();
        log.info("Datasource created.");
        return dataSource;
    }
}

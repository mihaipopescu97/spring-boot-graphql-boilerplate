package com.travelin.travelinapi.configurations;

import graphql.scalars.ExtendedScalars;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/21/2022, Wed
 **/
@Log4j2
@Configuration
public class GraphQLConfig {

    @Bean
    @Primary
    public GraphQlProperties getGraphQLProperties() {
        final GraphQlProperties properties = new GraphQlProperties();

        log.info("Setting GraphQL mapping at /api/travelin-service");
        properties.setPath("/api/travelin-service");

        log.info("Setting up GraphiQL mapping at /api/travelin-ui");
        properties.getGraphiql().setEnabled(true);
        properties.getGraphiql().setPath("/api/travelin-ui");

        log.info("GraphQL properties have been built.");
        return properties;
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        log.debug("Adding Date and Long scalars to GraphQL");
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.GraphQLLong);
    }

}

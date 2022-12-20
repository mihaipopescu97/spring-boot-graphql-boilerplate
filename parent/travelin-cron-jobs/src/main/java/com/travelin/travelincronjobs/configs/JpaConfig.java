package com.travelin.travelincronjobs.configs;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/29/2022, Thu
 **/

@Log4j2
@Configuration
public class JpaConfig {

    @Bean
    @Primary
    public JpaProperties getJpaProperties() {
        final JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.setShowSql(true);

        jpaProperties.setOpenInView(true);
        jpaProperties.setProperties(getProperties());

        log.info("JPA properties have been build. Attempting to apply them ...");
        return jpaProperties;
    }

    private Map<String, String> getProperties() {
        final Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("jakarta.persistence.sharedCache.mode", "ENABLE_SELECTIVE");

        return properties;
    }
}

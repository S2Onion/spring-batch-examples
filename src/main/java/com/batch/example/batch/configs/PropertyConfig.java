package com.batch.example.batch.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertyConfig {

    private static final String ADDITIONAL_PROPERTY_FILE_PATH = System.getenv("batch.dbconn.info");

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new FileSystemResource(ADDITIONAL_PROPERTY_FILE_PATH));
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(false);
        return propertySourcesPlaceholderConfigurer;
    }
}

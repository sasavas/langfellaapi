package com.zenkodyazilim.langfella.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class AppConfig {

    /**
     * Using the below configuration ensures Spring initialization failure if any ${} placeholder could not be resolved.
     * SOURCE: https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
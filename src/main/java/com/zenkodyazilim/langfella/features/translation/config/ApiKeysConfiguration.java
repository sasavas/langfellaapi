package com.zenkodyazilim.langfella.features.translation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api-keys.properties")
public class ApiKeysConfiguration {
}


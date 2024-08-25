package com.zenkodyazilim.langfella.features.translation.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "translator.google")
@Getter
@Setter
public class GoogleTranslatorConfig {
    private String projectId;
}
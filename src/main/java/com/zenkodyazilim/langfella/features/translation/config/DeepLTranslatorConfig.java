package com.zenkodyazilim.langfella.features.translation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "translator.deepl")
@Getter
@Setter
public class DeepLTranslatorConfig {
    private String apiKey;
}

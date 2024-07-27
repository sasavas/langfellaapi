package com.zenkodyazilim.langfella.features.article.entities.converters;

import com.zenkodyazilim.langfella.features.article.entities.Level;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LevelConverter implements AttributeConverter<Level, String> {
    @Override
    public String convertToDatabaseColumn(Level level) {
        if (level == null) {
            return null;
        }
        return level.code();
    }

    @Override
    public Level convertToEntityAttribute(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        return Level.findByLevelCode(code);
    }
}

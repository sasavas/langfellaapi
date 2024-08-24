package com.zenkodyazilim.langfella.features.word.dtos;

import com.zenkodyazilim.langfella.features.word.entities.Translation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationDTO {
    private String text;

    public static TranslationDTO of(Translation translation){
        return TranslationDTO.builder()
                .text(translation.getText())
                .build();
    }
}

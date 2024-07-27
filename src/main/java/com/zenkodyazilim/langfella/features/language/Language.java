package com.zenkodyazilim.langfella.features.language;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Language extends BaseEntity {
    @Id
    private String LanguageCode;
    private String FullName;
}

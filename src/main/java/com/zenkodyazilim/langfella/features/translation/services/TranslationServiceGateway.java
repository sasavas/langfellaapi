package com.zenkodyazilim.langfella.features.translation.services;

import com.zenkodyazilim.langfella.features.translation.dtos.TranslationDTO;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;

import java.util.Optional;

public interface TranslationServiceGateway {
    String getServiceName();
    Optional<TranslationDTO> translate(TranslationRequest translationRequest);
}
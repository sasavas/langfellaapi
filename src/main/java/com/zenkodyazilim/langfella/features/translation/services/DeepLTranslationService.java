package com.zenkodyazilim.langfella.features.translation.services;

import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationDTO;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class DeepLTranslationService implements TranslationServiceGateway {
    @Value("${translator.deepl.api-key}")
    private String apiKey;

    @Override
    public String getServiceName() {
        return "deepl";
    }

    @Override
    public Optional<TranslationDTO> translate(TranslationRequest translationRequest) {
        Translator translator = new Translator(apiKey);

        try {
            TextResult result = translator.translateText(
                    translationRequest.getWord(),
                    translationRequest.getSourceLanguage(),
                    translationRequest.getTargetLanguage());

            return Optional.of(new TranslationDTO(result.getText()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

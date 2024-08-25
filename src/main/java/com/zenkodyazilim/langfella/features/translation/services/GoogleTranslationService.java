package com.zenkodyazilim.langfella.features.translation.services;

import com.google.cloud.translate.v3.*;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationDTO;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
class GoogleTranslationService implements TranslationServiceGateway {
    private final GoogleTranslatorConfig config;

    @Override
    public String getServiceName() {
        return "google";
    }

    // Translate text to target language.
    @Override
    public Optional<TranslationDTO> translate(TranslationRequest translationRequest) {
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            LocationName parent = LocationName.of(config.getProjectId(), "global");

            // Supported Mime Types: https://cloud.google.com/translate/docs/supported-formats
            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode(translationRequest.getTargetLanguage())
                            .addContents(translationRequest.getWord())
                            .build();

            TranslateTextResponse response = client.translateText(request);

            var translation = response.getTranslationsList().stream().findFirst();
            if(translation.isPresent()){
                return Optional.of(new TranslationDTO(translation.get().getTranslatedText()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }
}


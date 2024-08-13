package com.zenkodyazilim.langfella.features.translation;

import com.zenkodyazilim.langfella.features.learner.api.LearnerServiceApi;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationDTO;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;
import com.zenkodyazilim.langfella.features.translation.services.TranslationServiceGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TranslationService {
    private final LearnerServiceApi learnerService;

    private final List<TranslationServiceGateway> services;

    private TranslationServiceGateway getTranslationService(String serviceName) {
        return services.stream().filter(t -> t.getServiceName().equalsIgnoreCase(serviceName)).findFirst().orElseThrow();
    }

    public Set<TranslationDTO> translate(long learnerId, TranslationRequest translationRequest) {
        // get all translator configs dynamically
        var translators = learnerService.getLearnerTranslators(learnerId);

        Set<TranslationDTO> translationDTOs = new HashSet<>();

        for (var translator : translators) {
            var translationService = getTranslationService(translator.getTranslatorName().toLowerCase());

            if (translationService != null) {
                var result = translationService.translate(translationRequest);
                result.ifPresent(translationDTOs::add);
            }
        }

        return translationDTOs;
    }
}

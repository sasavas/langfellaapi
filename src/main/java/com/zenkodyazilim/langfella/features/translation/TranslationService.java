package com.zenkodyazilim.langfella.features.translation;

import com.zenkodyazilim.langfella.features.learner.api.LearnerServiceApi;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationDTO;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;
import com.zenkodyazilim.langfella.features.translation.services.TranslationServiceGateway;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TranslationService {
    private static final Logger logger = LoggerFactory.getLogger(TranslationService.class);
    private final LearnerServiceApi learnerService;
    private final List<TranslationServiceGateway> services;

    private TranslationServiceGateway getTranslationService(String serviceName) {
        return services.stream().filter(t -> t.getServiceName().equalsIgnoreCase(serviceName)).findFirst().orElseThrow();
    }

    public Set<TranslationDTO> translate(long learnerId, TranslationRequest translationRequest) {
        // Get all translator configs dynamically
        var translators = learnerService.getLearnerTranslators(learnerId);

        // Create CompletableFuture for each translation task
        List<CompletableFuture<Optional<TranslationDTO>>> futures = translators.stream()
                .map(translator -> CompletableFuture.supplyAsync(() -> {
                    var translationService = getTranslationService(translator.getTranslatorName().toLowerCase());
                    if (translationService != null) {
                        try {
                            return translationService.translate(translationRequest);
                        } catch (Exception ex) {
                            logger.error("Error during translation with translator: {}, text: {}",
                                    translator.getTranslatorName(), translationRequest.getWord(), ex);
                            return Optional.<TranslationDTO>empty();
                        }
                    }
                    logger.warn("No translation service found for translator: {}", translator.getTranslatorName());
                    return Optional.<TranslationDTO>empty();
                }))
                .toList();

        // Wait for all tasks to complete and collect the results
        var translationDTOs = futures.stream()
                .map(CompletableFuture::join) // Join each future to get the result
                .filter(result -> result != null && result.isPresent()) // Filter out null results
                .map(Optional::get)
                .collect(Collectors.toSet());

        logger.info("Translation process completed for learnerId: {}. Word: {}, Translation: {}",
                learnerId, translationRequest.getWord(), translationDTOs.stream().map(TranslationDTO::getTranslation).collect(Collectors.joining(", ")));

        return translationDTOs;
    }
}

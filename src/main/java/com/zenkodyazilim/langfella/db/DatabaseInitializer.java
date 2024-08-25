package com.zenkodyazilim.langfella.db;

import com.zenkodyazilim.langfella.features.learner.LearnerService;
import com.zenkodyazilim.langfella.features.learner.dtos.LearnerCreateDTO;
import com.zenkodyazilim.langfella.features.learner.entities.Subscription;
import com.zenkodyazilim.langfella.features.learner.entities.Translator;
import com.zenkodyazilim.langfella.features.learner.repositories.TranslatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {
    private final LearnerService learnerService;
    private final TranslatorRepository translatorRepository;

    @Override
    public void run(ApplicationArguments args) {
        // add translators
        var googleT = Translator.of("google", "Google Translator");
        var deeplT = Translator.of("deepl", "DeepL Translator");
        translatorRepository.saveAll(List.of(googleT, deeplT));

        // add learner
        var learner = learnerService.createNewLearner(new LearnerCreateDTO(1, "en", Subscription.BASIC));
        learnerService.getAvailableTranslators().forEach(t -> learnerService.addTranslator(learner.getId(), t.getId()));
    }
}

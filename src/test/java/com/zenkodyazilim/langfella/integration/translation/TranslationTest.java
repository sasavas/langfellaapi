package com.zenkodyazilim.langfella.integration.translation;

import com.zenkodyazilim.langfella.features.language.Language;
import com.zenkodyazilim.langfella.features.learner.LearnerRepository;
import com.zenkodyazilim.langfella.features.learner.entities.Learner;
import com.zenkodyazilim.langfella.features.learner.entities.Subscription;
import com.zenkodyazilim.langfella.features.learner.entities.Translator;
import com.zenkodyazilim.langfella.features.translation.TranslationService;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;
import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TranslationTest {
    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private TranslationService translationService;

    @Test
    @Description("Test whether External Translator Services work")
    @Disabled("Requires external API calls")
    public void Test() {
        var learner = new Learner();
        learner.setId(1);
        learner.setMainLanguage(Language.ENGLISH.getLanguageCode());
        learner.getTargetLanguages().add(Language.TURKISH.getLanguageCode());
        learner.setSubscription(Subscription.BASIC);
        learner.getTranslators().add(Translator.of("google", "Google Translation API"));
        learner.getTranslators().add(Translator.of("deepl", "DeepL Translation API"));
        learnerRepository.save(learner);

        var result = translationService.translate(1, new TranslationRequest("en", "tr", "hello"));
        assertThat(result.size()).isGreaterThanOrEqualTo(1);

        result.forEach((t) -> System.out.println("translation result " + t.getTranslation()));
    }
}

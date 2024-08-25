package com.zenkodyazilim.langfella.integration.learner;

import com.zenkodyazilim.langfella.features.learner.repositories.LearnerRepository;
import com.zenkodyazilim.langfella.features.learner.LearnerService;
import com.zenkodyazilim.langfella.features.learner.repositories.TranslatorRepository;
import com.zenkodyazilim.langfella.features.learner.dtos.LearnerCreateDTO;
import com.zenkodyazilim.langfella.features.learner.entities.Learner;
import com.zenkodyazilim.langfella.features.learner.entities.Subscription;
import com.zenkodyazilim.langfella.features.learner.entities.Translator;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LearnerUpdateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private LearnerService learnerService;

    @Autowired
    private TranslatorRepository translatorRepository;

    @BeforeEach
    public void setUp(){
        // add learner
        Learner learner = new Learner();
        learner.setId(1);
        learner.setSubscription(Subscription.BASIC);
        learner.setMainLanguage("en");
        learnerService.createNewLearner(new LearnerCreateDTO(1, "en", Subscription.BASIC));

        // add a translator
        Translator translator = new Translator();
        translator.setId(1);
        translator.setName("Google");
        translator.setDescription("Google Translator");
        translatorRepository.save(translator);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void AddAndRemoveTranslatorToLearner() throws Exception{
        // perform adding translator
        mockMvc.perform(get("/api/learner/1/translators/1"))
                .andExpect(status().isNoContent());

        // verify
        var learner = learnerRepository.findById(1L).orElseThrow();
        assertEquals(1, learner.getTranslators().size());
        assertEquals("Google", learner.getTranslators().getFirst().getName());

        // perform removing translator
        mockMvc.perform(delete("/api/learner/1/translators/1"));

        // verify
        var learner2 = learnerRepository.findById(1L).orElseThrow();
        assertEquals(0, learner2.getTranslators().size());
    }
}

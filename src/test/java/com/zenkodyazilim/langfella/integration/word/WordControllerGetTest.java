package com.zenkodyazilim.langfella.integration.word;

import com.zenkodyazilim.langfella.features.word.WordRepository;
import com.zenkodyazilim.langfella.features.word.entities.ExampleSentence;
import com.zenkodyazilim.langfella.features.word.entities.Translation;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class WordControllerGetTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WordRepository wordRepository;

    @BeforeEach
    @DirtiesContext
    public void setUp() {
        Word word = new Word();
        word.setId(1L);
        word.setText("word");
        word.setSourceLanguageCode("en");
        word.setTargetLanguageCode("fr");
        word.setTranslations(Set.of(new Translation("mot")));
        word.setExampleSentences(Set.of(new ExampleSentence("Example sentence for word!")));

        wordRepository.save(word);
    }

    @Test
    public void testGetExistingWord() throws Exception {
        mockMvc.perform(get("/api/words/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.text").value("word"))
                .andExpect(jsonPath("$.translations[0].text").value("mot"))
                .andExpect(jsonPath("$.exampleSentences[0].text").value("Example sentence for word!"));
    }
}

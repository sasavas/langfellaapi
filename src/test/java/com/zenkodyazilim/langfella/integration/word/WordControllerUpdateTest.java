package com.zenkodyazilim.langfella.integration.word;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.zenkodyazilim.langfella.features.word.WordRepository;
import com.zenkodyazilim.langfella.features.word.WordService;
import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
import com.zenkodyazilim.langfella.features.word.dtos.UpdateWordDTO;
import com.zenkodyazilim.langfella.features.word.entities.ExampleSentence;
import com.zenkodyazilim.langfella.features.word.entities.Translation;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext
public class WordControllerUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private WordService wordService;

    @Test
    @Transactional
    @DirtiesContext
    public void testAddingWord() throws Exception {
        CreateWordDTO wordDTO = new CreateWordDTO(
                "word",
                "en",
                "tr",
                Set.of("kelime"),
                Optional.of("word example sentence"),
                null,
                null,
                null
        );

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());

        String json = objectMapper.writeValueAsString(wordDTO);

        mockMvc.perform(post("/api/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.exampleSentences.length()").value(1))
                .andExpect(jsonPath("$.translations.length()").value(1));

        var words = wordService.getWords();
        assertEquals(1, words.size());
        assertNotNull(words.getFirst().getTranslations().stream().findFirst().get().getWord());
        assertNotNull(words.getFirst().getExampleSentences().stream().findFirst().get().getWord());
    }

    @Test
    @Transactional
    @DirtiesContext
    public void testUpdateWord() throws Exception {
        // save the word
        Word word = new Word();
        word.setId(1L);
        word.setText("word");
        word.setSourceLanguageCode("en");
        word.setTargetLanguageCode("fr");
        word.setTranslations(Set.of(new Translation("mot")));
        word.setExampleSentences(Set.of(new ExampleSentence("Example sentence for word!")));
        wordRepository.save(word);

        UpdateWordDTO updateWordDTO = new UpdateWordDTO(
                null,
                Optional.of(Set.of()),
                Optional.of(Set.of("Another example sentence"))
        );

        // Convert the DTO to JSON
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module()); // required to serialize Optional fields.

        String json = objectMapper.writeValueAsString(updateWordDTO);

        var result = mockMvc.perform(put("/api/words/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exampleSentences.length()").value(2))
                .andExpect(jsonPath("$.translations.length()").value(1))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);
    }
}

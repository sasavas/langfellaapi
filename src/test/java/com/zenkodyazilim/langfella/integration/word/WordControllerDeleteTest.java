package com.zenkodyazilim.langfella.integration.word;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zenkodyazilim.langfella.features.word.WordRepository;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class WordControllerDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WordRepository wordRepository;

    @BeforeEach
    public void setUp() {
        // Populate the database with a word entry
        Word word = new Word();
        word.setId(1L);
        word.setText("Test");
        wordRepository.save(word);
    }

    @Test
    public void testDeleteWord() throws Exception {
        // Perform the delete request and expect a 204 No Content status
        mockMvc.perform(delete("/api/words/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verify that the word has been deleted from the repository
        boolean wordExists = wordRepository.existsById(1L);
        assertFalse(wordExists, "The word should have been deleted from the database.");

    }

    @Test
    public void testDeleteNonExistentWord() throws Exception {
        // Assuming this ID does not exist in the database
        long nonExistentWordId = 999L;

        // Perform the delete request and expect a 404 Not Found status
        mockMvc.perform(delete("/api/words/{id}", nonExistentWordId))
                .andExpect(status().isNoContent());
    }
}


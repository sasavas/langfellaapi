package com.zenkodyazilim.langfella.integration.language;

import com.zenkodyazilim.langfella.features.language.LanguageService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LanguageServiceTest {
    @Autowired
    private LanguageService languageService;

    @Test
    @Description("Can find English and Turkish languages")
    public void testExistsByLanguageCode_Found() {
        // Assert
        assertTrue(languageService.isValidLanguage("en"));
        assertTrue(languageService.isValidLanguage("tr"));
    }

    @Test
    public void testExistsByLanguageCode_NotFound() {
        // Assert
        assertFalse(languageService.isValidLanguage("fr"));
    }
}
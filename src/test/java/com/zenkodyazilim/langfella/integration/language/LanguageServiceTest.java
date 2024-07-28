package com.zenkodyazilim.langfella.integration.language;

import com.zenkodyazilim.langfella.features.language.LanguageRepository;
import com.zenkodyazilim.langfella.features.language.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LanguageServiceTest {

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private LanguageService languageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsValidLanguage_Found() {
        // Arrange
        when(languageRepository.existsByLanguageCode("EN")).thenReturn(true);

        // Act
        boolean result = languageService.isValidLanguage("EN");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValidLanguage_NotFound() {
        // Arrange
        when(languageRepository.existsByLanguageCode("FR")).thenReturn(false);

        // Act
        boolean result = languageService.isValidLanguage("FR");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValidLanguage_AnyCode() {
        // Arrange
        when(languageRepository.existsByLanguageCode(anyString())).thenReturn(false);

        // Act
        boolean result1 = languageService.isValidLanguage("EN");
        boolean result2 = languageService.isValidLanguage("XYZ");

        // Assert
        assertFalse(result1);
        assertFalse(result2);
    }
}
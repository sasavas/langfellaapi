package com.zenkodyazilim.langfella.integration.language;

import com.zenkodyazilim.langfella.features.language.Language;
import com.zenkodyazilim.langfella.features.language.LanguageRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class LanguageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LanguageRepository languageRepository;

    @Test
    @Description("Can find when a language exists in the database")
    public void testExistsByLanguageCode_Found() {
        // Arrange
        Language language = new Language();
        language.setLanguageCode("en");
        language.setFullName("English");
        entityManager.persist(language);
        entityManager.flush();

        // Act
        boolean exists = languageRepository.existsByLanguageCode("en");

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testExistsByLanguageCode_NotFound() {
        // Act
        boolean exists = languageRepository.existsByLanguageCode("fr");

        // Assert
        assertFalse(exists);
    }
}
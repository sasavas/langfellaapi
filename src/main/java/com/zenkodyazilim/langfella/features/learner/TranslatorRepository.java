package com.zenkodyazilim.langfella.features.learner;

import com.zenkodyazilim.langfella.features.learner.entities.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator, Long> {
}

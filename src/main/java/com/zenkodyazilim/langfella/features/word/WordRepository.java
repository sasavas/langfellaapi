package com.zenkodyazilim.langfella.features.word;

import com.zenkodyazilim.langfella.features.word.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByArticleId(long articleId);
}

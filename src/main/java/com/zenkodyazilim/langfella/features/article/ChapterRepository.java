package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.features.article.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}

package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.features.article.entities.Chapter;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public Chapter findById(Long chapterId) {
        return chapterRepository
                .findById(chapterId)
                .orElseThrow(() -> new EntityNotFoundException(Chapter.class.getSimpleName(), chapterId.toString()));
    }
}

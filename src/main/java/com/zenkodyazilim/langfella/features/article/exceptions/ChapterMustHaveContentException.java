package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityValidationException;

public class ChapterMustHaveContentException extends EntityValidationException {
    public ChapterMustHaveContentException() {
        super("Chapter must have at least one Content Item");
    }
}

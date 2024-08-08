package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityDomainException;

public class ChapterMustHaveContentException extends EntityDomainException {
    public ChapterMustHaveContentException() {
        super("Chapter must have at least one Content Item");
    }
}

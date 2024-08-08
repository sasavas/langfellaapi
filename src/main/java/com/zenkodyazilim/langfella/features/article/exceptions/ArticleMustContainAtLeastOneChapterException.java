package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityDomainException;

public class ArticleMustContainAtLeastOneChapterException extends EntityDomainException {
    public ArticleMustContainAtLeastOneChapterException() {
        super("Article must contain at least one chapter");
    }
}

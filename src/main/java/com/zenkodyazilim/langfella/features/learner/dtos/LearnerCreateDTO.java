package com.zenkodyazilim.langfella.features.learner.dtos;

import com.zenkodyazilim.langfella.features.learner.entities.Subscription;

public record LearnerCreateDTO(long userId, String mainLanguage, Subscription subscription) {

}

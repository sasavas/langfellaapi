package com.zenkodyazilim.langfella.features.learner.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class SubscriptionTracking extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Subscription subscription;
    private Date started;
    private Date ended;
}

package com.zenkodyazilim.langfella.features.learner.entities;

import lombok.Getter;

@Getter
public enum Subscription {
    BASIC("Basic Plan", 9.99),
    PREMIUM("Premium Plan", 19.99),
    GOLD("Gold Plan", 29.99);

    private final String description;
    private final double price;

    Subscription(String description, double price) {
        this.description = description;
        this.price = price;
    }
}
package com.zenkodyazilim.langfella.common.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CacheVerifier {

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void verifyCacheManager() {
        System.out.println("Cache Manager: " + cacheManager.getClass().getName());
    }
}

package com.zenkodyazilim.langfella.common.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("!test")
@EnableCaching
public class CacheConfig {
    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void verifyCacheManager() {
        System.out.println("Cache Manager: " + cacheManager.getClass().getName());
    }
}

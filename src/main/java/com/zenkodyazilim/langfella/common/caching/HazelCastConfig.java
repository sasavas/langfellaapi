package com.zenkodyazilim.langfella.common.caching;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class HazelCastConfig {
    @Bean
    @Profile({"dev", "prod"})
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("langfella-cache");
        mapConfig.setTimeToLiveSeconds(300); // 5 minutes TTL

        config.addMapConfig(mapConfig);

        // Create and return the Hazelcast instance
        return Hazelcast.newHazelcastInstance(config);
    }
}

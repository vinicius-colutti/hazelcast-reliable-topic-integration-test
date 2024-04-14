package com.colutti.hazelcastreliabletopicintegrationtest.configuration;

import com.colutti.hazelcastreliabletopicintegrationtest.cache.LocalCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalCacheConfiguration {

    @Bean
    public LocalCache createCache(){
        return new LocalCache();
    }
}

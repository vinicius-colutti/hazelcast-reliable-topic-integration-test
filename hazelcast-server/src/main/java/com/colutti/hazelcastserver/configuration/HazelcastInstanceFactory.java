package com.colutti.hazelcastserver.configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastInstanceFactory {

    @Bean
    public HazelcastInstance create(){
        return Hazelcast.newHazelcastInstance();
    }
}

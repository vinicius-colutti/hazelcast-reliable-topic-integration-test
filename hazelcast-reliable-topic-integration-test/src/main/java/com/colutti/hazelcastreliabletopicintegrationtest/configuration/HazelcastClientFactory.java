package com.colutti.hazelcastreliabletopicintegrationtest.configuration;

import com.colutti.hazelcastreliabletopicintegrationtest.cache.LocalCache;
import com.colutti.hazelcastreliabletopicintegrationtest.constant.HazelcastTopic;
import com.colutti.hazelcastreliabletopicintegrationtest.listener.LocalTopicListener;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientReliableTopicConfig;
import com.hazelcast.config.RingbufferConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.TopicOverloadPolicy;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class HazelcastClientFactory {

    private final LocalCache localCache;

    public HazelcastClientFactory(LocalCache localCache) {
        this.localCache = localCache;
    }

    @PostConstruct
    public void create() {
        ClientConfig config = new ClientConfig();
        RingbufferConfig ringbufferConfig = new RingbufferConfig(HazelcastTopic.NAME);
        ringbufferConfig.setCapacity(10000);

        ClientReliableTopicConfig topicConfig = new ClientReliableTopicConfig(HazelcastTopic.NAME);
        topicConfig.setTopicOverloadPolicy(TopicOverloadPolicy.BLOCK)
                .setReadBatchSize(10);
        config.addReliableTopicConfig(topicConfig);
        HazelcastInstance clientHazelcastListener = HazelcastClient.newHazelcastClient(config);
        ITopic<String> localTopic = clientHazelcastListener.getReliableTopic(HazelcastTopic.NAME);
        localTopic.addMessageListener(new LocalTopicListener(this.localCache));
    }
}

package com.colutti.hazelcastreliabletopicintegrationtest.hazelcast;

import com.colutti.hazelcastreliabletopicintegrationtest.cache.LocalCache;
import com.colutti.hazelcastreliabletopicintegrationtest.constant.HazelcastTopic;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HazelcastTopicIntegrationTest {

    private static HazelcastInstance HAZELCAST_INSTANCE;

    @Autowired
    private LocalCache localCache;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeAll
    static void setupHazelcastInstance(){
        HAZELCAST_INSTANCE = Hazelcast.newHazelcastInstance();
    }

    @Test
    void teste() throws InterruptedException {
        HAZELCAST_INSTANCE.getReliableTopic(HazelcastTopic.NAME).publish("teste");
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(5, TimeUnit.SECONDS);
        Object publishedMessage = localCache.getCache("teste");
        if(null == publishedMessage){
            //try again
            latch.await(5, TimeUnit.SECONDS);
            publishedMessage = localCache.getCache("teste");
            assertNotNull(publishedMessage);
        }else{
            assertNotNull(publishedMessage);
        }
    }
}

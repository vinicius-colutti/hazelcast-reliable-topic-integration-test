package com.colutti.hazelcastserver.controller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class PublishOnLocalTopicController {

    private final HazelcastInstance hazelcastInstance;

    public PublishOnLocalTopicController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping
    public String publishMessage(@RequestBody String messageToPublish){
        this.hazelcastInstance.getReliableTopic("local_topic").publish(messageToPublish);
        return "published...";
    }
}

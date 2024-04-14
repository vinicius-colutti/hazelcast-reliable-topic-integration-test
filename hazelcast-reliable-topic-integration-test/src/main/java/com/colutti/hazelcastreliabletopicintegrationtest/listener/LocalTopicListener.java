package com.colutti.hazelcastreliabletopicintegrationtest.listener;

import com.colutti.hazelcastreliabletopicintegrationtest.cache.LocalCache;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.ReliableMessageListener;

public class LocalTopicListener implements ReliableMessageListener<String> {

    private final LocalCache localCache;

    public LocalTopicListener(LocalCache localCache) {
        this.localCache = localCache;
    }

    @Override
    public long retrieveInitialSequence() {
        return 0;
    }

    @Override
    public void storeSequence(long sequence) {

    }

    @Override
    public boolean isLossTolerant() {
        return false;
    }

    @Override
    public boolean isTerminal(Throwable failure) {
        return false;
    }

    @Override
    public void onMessage(Message<String> message) {
        System.out.println("received message: "+ message.getMessageObject());
        this.localCache.put(message.getMessageObject());
    }
}

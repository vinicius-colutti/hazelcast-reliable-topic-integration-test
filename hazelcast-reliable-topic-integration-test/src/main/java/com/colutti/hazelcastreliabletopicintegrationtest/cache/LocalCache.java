package com.colutti.hazelcastreliabletopicintegrationtest.cache;

import java.util.*;

public class LocalCache {

    private final Map<String, String> cache;

    public LocalCache(){
        cache = new HashMap<>();
    }

    public String getCache(String message){
        return cache.get(message);
    }

    public void put(String message){
        cache.put(message, UUID.randomUUID().toString());
    }
}

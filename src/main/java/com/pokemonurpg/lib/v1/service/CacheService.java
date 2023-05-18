package com.pokemonurpg.lib.v1.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CacheService {

    private final Map<Class<?>, Map<Integer, Object>>   cacheById = new HashMap<>();
    private final Map<Class<?>, Map<String, Object>>    cacheByName = new HashMap<>();

    public <T> void putById(Class<?> T, Integer id, T object) {
        if (id == null || object == null) {
            throw new NullPointerException();
        }
        if (!cacheById.containsKey(T)) {
            cacheById.put(T, new HashMap<>());
        }
        cacheById.get(T).put(id, object);
    }

    public <T> T getById(Class<T> T, Integer id) {
        return cacheById.containsKey(T) ? T.cast(cacheById.get(T).get(id)) : null;
    }

    public <T> void putByName(Class<?> T, String name, T object) {
        if (name == null || object == null) {
            throw new NullPointerException();
        }
        if (!cacheByName.containsKey(T)) {
            cacheByName.put(T, new HashMap<>());
        }
        cacheByName.get(T).put(name, object);
    }

    public <T> T getByName(Class<T> T, String name) {
        return cacheByName.containsKey(T) ? T.cast(cacheByName.get(T).get(name)) : null;
    }

}

package com.pokemonurpg.configuration.v2.shared.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public interface EntityWrapper {    
    
    @SuppressWarnings("unchecked")
    static <S extends EntityWrapper> S wrapEntityInClass(Object entity, Class<S> cls) {
        try {
            Constructor<S> constructor = (Constructor<S>) cls.getConstructors()[0];
            return (S) constructor.newInstance(entity);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | SecurityException e) {
            return null;
        }
    }
    
    static <S extends EntityWrapper> List<S> wrapEntitiesInClass(List<?> entities, Class<S> cls) {
            return entities.stream()
                .map(entity -> 
                wrapEntityInClass(entity, cls)).collect(Collectors.toList());
    }
}
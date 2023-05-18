package com.pokemonurpg.lib.v1.aspect;

import com.pokemonurpg.lib.v1.annotations.Cached;
import com.pokemonurpg.lib.v1.service.CacheService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.inject.Provider;

import java.lang.reflect.Method;

@Aspect
@Component
public class CacheAspect {

    @Autowired
    private Provider<CacheService> cacheProvider;


    @Around(value = "@annotation(com.pokemonurpg.core.annotation.Cached)")
    public Object handleCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature methodSignature = joinPoint.getSignature();
        MethodSignature signature = (MethodSignature) methodSignature;
        Method method = joinPoint.getTarget().getClass()
                .getMethod(signature.getMethod().getName(), signature.getMethod().getParameterTypes());
        Cached annotation =  method.getAnnotation(Cached.class);

        Class<?> T = annotation.type();

        Class<?> idType = signature.getParameterTypes()[0];
        Object[] methodArguments = joinPoint.getArgs();

        if (signature.getParameterTypes().length == 1) {
            if (methodArguments.length == 1 && methodArguments[0] != null) {
                if (idType == Integer.class || idType == int.class) {
                    return getCachedObjectById((Integer) methodArguments[0], T, joinPoint);
                }
                else if (idType == String.class) {
                    return getCachedObjectByName((String) methodArguments[0], T, joinPoint);
                }
            }
        }
        return joinPoint.proceed();
    }

    public Object getCachedObjectById(Integer id, Class<?> T, ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = cacheProvider.get().getById(T, id);
        if (object != null) {
            return object;
        }
        else {
            object = joinPoint.proceed();
            cacheProvider.get().putById(T, id, object);
            return object;
        }
    }

    public Object getCachedObjectByName(String name, Class<?> T, ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = cacheProvider.get().getByName(T, name);
        if (object != null) {
            return object;
        }
        else {
            object = joinPoint.proceed();
            cacheProvider.get().putByName(T, name, object);
            return object;
        }
    }
}

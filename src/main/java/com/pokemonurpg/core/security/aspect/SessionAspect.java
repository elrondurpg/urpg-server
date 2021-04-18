package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.service.AuthorizationHeaderService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.inject.Provider;

@Aspect
@Configuration
public class SessionAspect {

    @Resource
    private Provider<AuthorizationHeaderService> sessionServiceProvider;

    @Before("bean(*Controller)")
    public void before() {
        sessionServiceProvider.get().getUser();
    }
}

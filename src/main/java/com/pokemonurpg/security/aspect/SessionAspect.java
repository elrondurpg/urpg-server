package com.pokemonurpg.security.aspect;

import com.pokemonurpg.security.service.SessionService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.inject.Provider;

@Aspect
@Configuration
@Order(0)
public class SessionAspect {

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    void createSession() {
        sessionServiceProvider.get().createSession();
    }
}

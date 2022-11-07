package com.pokemonurpg.lib.security.v1;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.servlet.HandlerInterceptor;

import com.pokemonurpg.security.service.SessionService;

public class SessionCreator implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(AuthorizationHandler.class);

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        sessionServiceProvider.get().createSession();
        return true;
    }
}

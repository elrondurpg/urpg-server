package com.pokemonurpg.login.v1;

import com.pokemonurpg.lib.v1.annotations.*;
import com.pokemonurpg.lib.v1.service.RequestPathVariableService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthorizationHandler implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(AuthorizationHandler.class);

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @Override
    @Transactional
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        try {
            if (handler != null) {
                HandlerMethod method = (HandlerMethod) handler;

                sessionServiceProvider.get().createSession();

                if (allowAll(method) ||
                    allowAuthenticated(method) ||
                    allowThisMember(method) ||
                    allowTheOwner(method) ||
                    allowAuthorized(method)) {
                    return true;
                }
                else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                }
            }
        } catch (Exception e) {
            log.catching(e);
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return false;
    }

    public boolean allowAll(HandlerMethod method) {
        return method.getMethodAnnotation(AllowAll.class) != null;
    }

    public boolean allowAuthenticated(HandlerMethod method) {
        return method.getMethodAnnotation(AllowAuthenticated.class) != null && sessionServiceProvider.get().getAuthenticatedMember() != null;
    }

    public boolean allowThisMember(HandlerMethod method) {
        AllowThisMember annotation = method.getMethodAnnotation(AllowThisMember.class);
        if (annotation != null) {
            Integer dbid = requestPathVariableService.findIntByName("dbid");
            if (dbid != null) {
                Member requestor = sessionServiceProvider.get().getAuthenticatedMember();
                return requestor != null && dbid.equals(requestor.getDbid());
            }
        }
        return false;
    }

    public boolean allowTheOwner(HandlerMethod method) {
        AllowTheOwner annotation = method.getMethodAnnotation(AllowTheOwner.class);
        if (annotation != null) {
            Integer dbid = requestPathVariableService.findIntByName("dbid");
            if (annotation.type() == OwnedPokemon.class) {
                return allowThePokemonOwner(dbid);
            }
        }
        return false;
    }

    private boolean allowThePokemonOwner(Integer dbid) {
        OwnedPokemon pokemon = ownedPokemonService.findByDbid(dbid);
        if (pokemon != null && pokemon.getTrainer() != null) {
            Member requestor = sessionServiceProvider.get().getAuthenticatedMember();
            return requestor != null && Objects.equals(pokemon.getTrainer().getDbid(), requestor.getDbid());
        }
        return false;
    }

    public boolean allowAuthorized(HandlerMethod method) {
        AllowAuthorized authorizedAnnotation = method.getMethodAnnotation(AllowAuthorized.class);
        return authorizedAnnotation != null && authorizationService.isAuthorized(authorizedAnnotation.permission());
    }
}

package com.pokemonurpg.lib.security.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthenticated;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.security.annotation.AllowTheOwner;
import com.pokemonurpg.security.annotation.AllowThisMember;

public class PreRequestAuthorizationInterceptor implements HandlerInterceptor, ApplicationContextAware {
    private static final Logger log = LogManager.getLogger(PreRequestAuthorizationInterceptor.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean authorized = false;
        try {
            if (handlerHasOtherAuthAnnotation(handler)) return true;
            AuthorizationService service = determineAuthorizationService(handler);
            authorized = service.isAuthorized(request);
            updateResponseBasedOnAuthorized(response, authorized);
        } catch (Exception e) {
            catchAuthorizationExceptionAndUpdateResponse(e, response);
        }
        return authorized;
    }

    private boolean handlerHasOtherAuthAnnotation(Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        return method.getMethodAnnotation(AllowAll.class) != null ||
        method.getMethodAnnotation(AllowAuthorized.class) != null ||
        method.getMethodAnnotation(AllowTheOwner.class) != null ||
        method.getMethodAnnotation(AllowThisMember.class) != null ||
        method.getMethodAnnotation(AllowAuthenticated.class) != null;
    }

    private AuthorizationService determineAuthorizationService(Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        CheckAuthorization annotation = method.getMethodAnnotation(CheckAuthorization.class);
        AuthorizationType authorizationType = annotation.authorizationType();
        Class<? extends AuthorizationService> serviceClass = authorizationType.getAuthorizationServiceClass();
        return getAuthorizationService(serviceClass);
    }

    private AuthorizationService getAuthorizationService(Class<? extends AuthorizationService> serviceClass) {
        ConfigurableListableBeanFactory beanFactory = ((AbstractApplicationContext) applicationContext).getBeanFactory();
        Map<String, ? extends AuthorizationService> services = beanFactory.getBeansOfType(serviceClass);
        return (AuthorizationService) services.values().toArray()[0];
    }

    private void updateResponseBasedOnAuthorized(HttpServletResponse response, boolean authorized) {
        if (!authorized) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private void catchAuthorizationExceptionAndUpdateResponse(Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.catching(e);
    }

}

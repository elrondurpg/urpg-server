package com.pokemonurpg.lib.management.v1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import com.pokemonurpg.entities.v1.site.Flag;
import com.pokemonurpg.configuration.v1.site.flag.service.FlagService;

public class FeatureFlagInterceptor implements HandlerInterceptor {

    @Resource
    private FlagService flagService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        EnabledByFlag annotation = getAnnotation(handler);
        boolean valid = isAnnotationValid(annotation);
        if (valid) {
            Flag flag = flagService.findByName(annotation.flag());
            if (flag == null || !flag.isTrue()) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, annotation.message());
            }
        }
        return true;
    }

    private EnabledByFlag getAnnotation(Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        return method.getMethodAnnotation(EnabledByFlag.class);
    }

    private boolean isAnnotationValid(EnabledByFlag annotation) {
        return annotation != null && StringUtils.isNotBlank(annotation.flag());
    }
}

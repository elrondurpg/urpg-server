package com.pokemonurpg.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class RequestPathVariableService {
    public String findStringByName(String name) {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                return (String) pathVariables.get(name);
            }
            else return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer findIntByName(String name) {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                if (pathVariables.get(name) instanceof String) {
                    return Integer.parseInt((String) pathVariables.get(name));
                }
                return (Integer) pathVariables.get(name);
            } else return null;
        } catch (Exception e) {
            return null;
        }
    }

}

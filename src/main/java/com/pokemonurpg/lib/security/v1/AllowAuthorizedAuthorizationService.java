package com.pokemonurpg.lib.security.v1;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import com.pokemonurpg.configuration.v1.member.permission.PermissionService;
import com.pokemonurpg.lib.resource.v1.model.ApiResource;
import com.pokemonurpg.lib.resource.v1.repository.ApiResourceRepository;
import com.pokemonurpg.security.service.SessionService;

@Service
public class AllowAuthorizedAuthorizationService implements AuthorizationService {
    @Resource
    private Provider<SessionService> sessionServiceProvider;

    @Resource
    private PermissionService permissionService;

    @Resource
    private ApiResourceRepository apiResourceRepository;

    @Override
    public boolean isAuthorized(HttpServletRequest request) {
        try {
            String permissionName = buildPermissionNameFromRequest(request);
            return sessionServiceProvider.get().isMemberAuthorizedFor(permissionName);
        }
        catch (Exception e) {
            return false;
        }
    }    

    private ApiResource getApiResourceFromRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        LinkedHashMap<String, String> attributes = (LinkedHashMap<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return apiResourceRepository.findByUrl(stripAttributesFromUrl(attributes, requestUri));
    }

    private String stripAttributesFromUrl(LinkedHashMap<String, String> attributes, String url) {
        for (String value : attributes.values()) {
            url = url.replaceAll("/" + value, "");
        }
        return url;
    }

    private String buildPermissionNameFromRequest(HttpServletRequest request) {
        ApiResource apiResource = getApiResourceFromRequest(request);
        String httpMethod = request.getMethod();
        return getPermissionTypeForHttpMethod(httpMethod) + " " + apiResource.getName();
    }

    private String getPermissionTypeForHttpMethod(String httpMethod) {
        if ("GET".equalsIgnoreCase(httpMethod)) {
            return "Read";
        }
        else if ("POST".equalsIgnoreCase(httpMethod) || "PUT".equalsIgnoreCase(httpMethod)) {
            return "Write";
        }
        else if ("DELETE".equalsIgnoreCase(httpMethod)) {
            return "Delete";
        }
        else throw new IllegalStateException();
    }


}

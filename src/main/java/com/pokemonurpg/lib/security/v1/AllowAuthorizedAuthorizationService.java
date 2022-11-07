package com.pokemonurpg.lib.security.v1;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.pokemonurpg.lib.resource.v1.model.ApiResource;
import com.pokemonurpg.lib.resource.v1.repository.ApiResourceRepository;
import com.pokemonurpg.member.service.PermissionService;
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
        return apiResourceRepository.findByUrl(requestUri);
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

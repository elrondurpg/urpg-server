package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.models.Permission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.*;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SessionService {
    @Resource
    private AuthorizationCredentialsService authorizationCredentialsService;

    @Resource
    private AuthenticationService authenticationService;

    private Member authenticatedMember = null;
    private final Set<Permission> authenticatedPermissions = new HashSet<>();
    private String accessToken = null;

    public void createSession() {
        String[] credentials = authorizationCredentialsService.getCredentials();
        if (credentials != null) {
            Member member = authenticationService.authenticate(credentials[0], credentials[1]);
            if (member != null) {
                setAuthenticatedMember(member);
                member.getRoles().forEach(role -> authenticatedPermissions.addAll(role.getPermissions()));
                setAccessToken(credentials[1]);
            }
        }
    }

    public Member getAuthenticatedMember() {
        return authenticatedMember;
    }

    private void setAuthenticatedMember(Member authenticatedMember) {
        this.authenticatedMember = authenticatedMember;
    }

    public Set<Permission> getAuthenticatedPermissions() {
        return authenticatedPermissions;
    }

    public String getAccessToken() {
        return accessToken;
    }

    private void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
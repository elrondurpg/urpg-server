package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.service.SessionService;
import com.pokemonurpg.member.models.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Provider;

@Service
public class AuthorizationService
{
    @Resource
    private Provider<SessionService> sessionServiceProvider;

    public boolean isAuthorized(String permission) {
        SessionService sessionService = sessionServiceProvider.get();
        Member member = sessionService.getAuthenticatedMember();
        if (member != null) {
            if (!member.getBanned()) {
                for (Role role : member.getRoles()) {
                    for (Permission perm : role.getPermissions()) {
                        if (perm.getName().equals(permission)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}

package com.pokemonurpg.security.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.member.Member;

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
        return member != null &&
            !member.getBanned() &&
            sessionService.getAuthenticatedPermissions().stream()
                .anyMatch(permissionObj -> permissionObj != null && permissionObj.getName().equals(permission));
    }

}

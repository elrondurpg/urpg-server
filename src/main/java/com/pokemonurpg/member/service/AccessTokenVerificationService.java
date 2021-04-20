package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.service.HashService;
import com.pokemonurpg.member.models.Member;

import javax.annotation.Resource;

public class AccessTokenVerificationService {

    @Resource
    private HashService hashService;

    public boolean verify(Member member, String providedAccessToken) {
        String accessToken = member.getAccessToken();
        int salt = member.getSalt();

        if (accessToken == null || accessToken.isEmpty()) return false;

        try {
            String loginCheck = hashService.hash(providedAccessToken + salt);
            return loginCheck.equals(accessToken);
        } catch (Exception e) {
            return false;
        }
    }
}

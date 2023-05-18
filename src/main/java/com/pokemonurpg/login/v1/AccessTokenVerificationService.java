package com.pokemonurpg.login.v1;

import com.pokemonurpg.entities.v1.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
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

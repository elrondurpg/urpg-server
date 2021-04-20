package com.pokemonurpg.member.service;

import com.mysql.cj.Session;
import com.pokemonurpg.member.models.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HumanAuthenticationService {

    @Resource
    private AccessTokenVerificationService accessTokenVerificationService;

    @Resource
    private SessionExpirationService sessionExpirationService;

    public Member authenticate(Member member, String accessToken) {
        if (member != null && !member.isBot()) {
            if (accessTokenVerificationService.verify(member, accessToken)) {
                if (!sessionExpirationService.isExpired(member.getSessionExpire())) {
                    return member;
                }
            }
        }
        return null;
    }
}

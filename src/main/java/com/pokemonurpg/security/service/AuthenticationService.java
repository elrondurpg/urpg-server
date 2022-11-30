package com.pokemonurpg.security.service;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthenticationService {

    @Resource
    private MemberService memberService;

    @Resource
    private AccessTokenVerificationService accessTokenVerificationService;

    @Resource
    private SessionExpirationService sessionExpirationService;

    public Member authenticate(String discordId, String accessToken) {
        Member member = memberService.findByDiscordId(discordId);
        if (member != null) {
            if (accessTokenVerificationService.verify(member, accessToken)) {
                if (!sessionExpirationService.isExpired(member.getSessionExpire())) {
                    return member;
                }
            }
        }
        return null;
    }
}

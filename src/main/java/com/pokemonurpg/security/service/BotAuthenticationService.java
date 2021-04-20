package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BotAuthenticationService {

    @Resource
    private AccessTokenVerificationService accessTokenVerificationService;

    public Member authenticate(Member bot, String accessToken) {
        if (bot != null && bot.isBot()) {
            if (accessTokenVerificationService.verify(bot, accessToken)) {
                return bot;
            }
        }
        return null;
    }

}

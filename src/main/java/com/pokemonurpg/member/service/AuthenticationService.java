package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.DiscordUserResponse;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.core.security.service.AesEncryptionService;
import com.pokemonurpg.core.security.service.OAuthService;
import com.pokemonurpg.core.security.service.SessionService;
import com.pokemonurpg.member.input.LoginInputDto;
import com.pokemonurpg.member.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.inject.Provider;

@Service
public class AuthenticationService {

    @Resource
    private MemberService memberService;

    @Resource
    private BotAuthenticationService botAuthenticationService;

    @Resource
    private HumanAuthenticationService humanAuthenticationService;

    public Member authenticate(String discordId, String accessToken) {
        Member matchedMember = memberService.findByDiscordId(discordId);
        if (matchedMember != null) {
            if (matchedMember.isBot()) {
                return botAuthenticationService.authenticate(matchedMember, accessToken);
            }
            else return humanAuthenticationService.authenticate(matchedMember, accessToken);
        }
        else return null;
    }
}

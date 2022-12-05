package com.pokemonurpg.security.service;

import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.entities.v1.member.Member;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private MemberService memberService;

    @Resource
    private OAuthService oAuthService;

    public SessionDto login(LoginInputDto input) {
        OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
        if (accessTokenResponse != null && accessTokenResponse.isValid()) {
            DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
            if (discordUserResponse != null && discordUserResponse.isValid()) {
                Member member = memberService.findByDiscordId(discordUserResponse.getId());
                if (member != null) {
                    memberService.update(member, accessTokenResponse);
                    return new SessionDto(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while logging you in.");
    }
}

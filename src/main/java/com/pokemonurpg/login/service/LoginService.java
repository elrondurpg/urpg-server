package com.pokemonurpg.login.service;

import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.dto.SessionDto;
import com.pokemonurpg.login.models.DiscordUserResponse;
import com.pokemonurpg.login.models.OAuthAccessTokenResponse;
import com.pokemonurpg.login.dto.LoginInputDto;
import com.pokemonurpg.entities.Member;
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

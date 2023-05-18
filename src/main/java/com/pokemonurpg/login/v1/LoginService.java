package com.pokemonurpg.login.v1;

import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.entities.v1.Member;
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

    public Session login(LoginRequest input) {
        OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
        if (accessTokenResponse != null && accessTokenResponse.isValid()) {
            DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
            if (discordUserResponse != null && discordUserResponse.isValid()) {
                Member member = memberService.findByDiscordId(discordUserResponse.getId());
                if (member != null) {
                    memberService.update(member, accessTokenResponse);
                    return new Session(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while logging you in.");
    }
}

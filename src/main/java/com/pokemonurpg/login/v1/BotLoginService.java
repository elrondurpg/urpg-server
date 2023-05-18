package com.pokemonurpg.login.v1;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class BotLoginService {
    @Resource
    private AuthorizationCredentialsService authorizationCredentialsService;

    @Resource
    private OAuthService oAuthService;

    @Resource
    private MemberService memberService;

    public SessionDto login() {
        String[] credentials = authorizationCredentialsService.getCredentials();
        if (credentials != null && credentials.length == 2) {
            OAuthAccessTokenResponse accessTokenResponse = oAuthService.getAccessTokenForClientCredentials(credentials[0], credentials[1]);
            if (accessTokenResponse != null && accessTokenResponse.isValid()) {
                Member member = memberService.findByDiscordId(credentials[0]);
                if (member != null && Boolean.TRUE.equals(member.getBot())) {
                    memberService.update(member, accessTokenResponse);
                    return new SessionDto(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while logging you in.");
    }
}

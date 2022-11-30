package com.pokemonurpg.account.v1.register.newplayer;

import org.springframework.stereotype.Service;
import com.pokemonurpg.account.v1.register.RegisterPlayerHandler;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.lib.security.v1.Session;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

@Service
public class RegisterNewPlayerHandler extends RegisterPlayerHandler<RegisterNewPlayerDto, CreateNewPlayerHandler> {

    protected Session createSessionForRegistrationIfSuccessful(RegisterNewPlayerDto input) {
        OAuthAccessTokenResponse token = oAuthService.exchangeCodeForAccessToken(input.getCode());
        DiscordUserResponse user = oAuthService.getDiscordId(token.getAccessToken());
        CreateNewPlayerDto createNewPlayerDto = 
            CreateNewPlayerDto.builder()
                .accessToken(token)
                .discordId(user.getId())
                .input(input)
                .build();
        Member member = handler.handle(createNewPlayerDto);
        return createSession(member, token.getAccessToken());
    }
}

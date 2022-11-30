package com.pokemonurpg.account.v1.register.returningplayer;

import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.RegisterPlayerDto;
import com.pokemonurpg.account.v1.register.RegisterPlayerHandler;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.lib.security.v1.Session;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

@Service
public class RegisterReturningPlayerHandler extends RegisterPlayerHandler<RegisterPlayerDto, CreateReturningPlayerHandler> {

    protected Session createSessionForRegistrationIfSuccessful(RegisterPlayerDto input) {
        OAuthAccessTokenResponse token = oAuthService.exchangeCodeForAccessToken(input.getCode());
        DiscordUserResponse user = oAuthService.getDiscordId(token.getAccessToken());
        CreateReturningPlayerDto dto = 
        CreateReturningPlayerDto.builder()
                .accessToken(token)
                .discordId(user.getId())
                .input(input)
                .build();
        Member member = handler.handle(dto);
        return createSession(member, token.getAccessToken());
    }
}

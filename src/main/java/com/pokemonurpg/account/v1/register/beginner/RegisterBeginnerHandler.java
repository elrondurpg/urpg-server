package com.pokemonurpg.account.v1.register.beginner;

import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerHandler;
import com.pokemonurpg.configuration.v1.create.member.beginner.CreateBeginnerRequest;
import com.pokemonurpg.configuration.v1.create.member.beginner.CreateBeginnerResponse;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

@Service
public class RegisterBeginnerHandler extends RegisterPlayerHandler<
    RegisterBeginnerRequest, 
    RegisterBeginnerResponse,
    CreateBeginnerRequest,
    CreateBeginnerResponse> {

    @Override
    protected CreateBeginnerRequest createMemberRequest(RegisterBeginnerRequest request) {
        OAuthAccessTokenResponse token = oAuthService.exchangeCodeForAccessToken(request.getCode());
        DiscordUserResponse user = oAuthService.getDiscordId(token.getAccessToken());
        return CreateBeginnerRequest.builder()
            .accessToken(token)
            .discordId(user.getId())
            .name(request.getName())
            .code(request.getCode())
            .starter(request.getStarter())
            .build();
    }

    @Override
    protected RegisterBeginnerResponse createResponse(CreateBeginnerResponse response) {
        return RegisterBeginnerResponse.builder()
            .accessToken(response.getAccessToken())
            .name(response.getName())
            .discordId(response.getDiscordId())
            .roles(response.getRoles())
            .starter(response.getStarter())
            .build();
    }
}

package com.pokemonurpg.account.v1.register.veteran;

import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerRequest;
import com.pokemonurpg.account.v1.register.common.RegisterPlayerResponse;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberRequest;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberResponse;
import com.pokemonurpg.account.v1.register.common.RegisterPlayerHandler;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

@Service
public class RegisterVeteranHandler extends RegisterPlayerHandler<
    RegisterPlayerRequest, 
    RegisterPlayerResponse,
    CreateMemberRequest,
    CreateMemberResponse> {

    @Override
    protected CreateMemberRequest createMemberRequest(RegisterPlayerRequest request) {
        OAuthAccessTokenResponse token = oAuthService.exchangeCodeForAccessToken(request.getCode());
        DiscordUserResponse user = oAuthService.getDiscordId(token.getAccessToken());
        return CreateMemberRequest.builder()
            .accessToken(token)
            .discordId(user.getId())
            .name(request.getName())
            .code(request.getCode())
            .build();
    }

    @Override
    protected RegisterPlayerResponse createResponse(CreateMemberResponse response) {
        return RegisterPlayerResponse.builder()
            .accessToken(response.getAccessToken())
            .name(response.getName())
            .discordId(response.getDiscordId())
            .roles(response.getRoles())
            .build();
    }
}

package com.pokemonurpg.account.v1.register.veteran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.common.RegisterMemberRequest;
import com.pokemonurpg.account.v1.register.common.RegisterMemberResponse;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberHandler;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberRequest;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberResponse;
import com.pokemonurpg.account.v1.register.common.RegisterMemberHandler;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.OAuthService;

@Service
public class RegisterVeteranHandler extends RegisterMemberHandler<
    RegisterMemberRequest, 
    RegisterMemberResponse,
    CreateMemberRequest,
    CreateMemberResponse> {

    @Autowired
    public RegisterVeteranHandler(OAuthService oAuthService,
            CreateMemberHandler<CreateMemberRequest, CreateMemberResponse> memberHandler) {
        super(oAuthService, memberHandler);
    }

    @Override
    protected CreateMemberRequest createMemberRequest(RegisterMemberRequest request) {
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
    protected RegisterMemberResponse createResponse(CreateMemberResponse response) {
        return RegisterMemberResponse.builder()
            .accessToken(response.getAccessToken())
            .name(response.getName())
            .discordId(response.getDiscordId())
            .roles(response.getRoles())
            .build();
    }
}

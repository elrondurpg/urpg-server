package com.pokemonurpg.account.v1.register.beginner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateBeginnerRequest;
import com.pokemonurpg.account.v1.register.beginner.internal.CreateBeginnerResponse;
import com.pokemonurpg.account.v1.register.common.RegisterMemberHandler;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberHandler;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.OAuthService;

@Service
public class RegisterBeginnerHandler extends RegisterMemberHandler<
    RegisterBeginnerRequest, 
    RegisterBeginnerResponse,
    CreateBeginnerRequest,
    CreateBeginnerResponse> {

    @Autowired
    public RegisterBeginnerHandler(OAuthService oAuthService,
            CreateMemberHandler<CreateBeginnerRequest, CreateBeginnerResponse> memberHandler) {
        super(oAuthService, memberHandler);
    }

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

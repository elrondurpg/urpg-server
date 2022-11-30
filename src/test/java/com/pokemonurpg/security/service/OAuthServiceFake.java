package com.pokemonurpg.security.service;

import com.pokemonurpg.account.v1.register.beginner.RegisterBeginnerRequestFake;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.DiscordUserResponseFake;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponseFake;

public class OAuthServiceFake extends OAuthService {
    @Override
    public OAuthAccessTokenResponse exchangeCodeForAccessToken(String code) {
        if (RegisterBeginnerRequestFake.CODE.equals(code)) {
            return new OAuthAccessTokenResponseFake();
        }
        else {
            throw new AssertionError("No test case was supplied for the provided code.");
        }
    }

    @Override
    public DiscordUserResponse getDiscordId(String accessToken) {
        if (OAuthAccessTokenResponseFake.ACCESS_TOKEN.equals(accessToken)) {
            return new DiscordUserResponseFake();
        }
        else {
            throw new AssertionError("No test case was supplied for the provided access token.");
        }
    }
}

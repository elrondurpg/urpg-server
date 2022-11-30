package com.pokemonurpg.account.v1.register.beginner.internal;

import com.pokemonurpg.security.models.OAuthAccessTokenResponseFake;

public class CreateBeginnerRequestFake extends CreateBeginnerRequest {
    public final static String CODE = "CODE";
    public final static String DISCORD_ID = "DISCORD_ID";
    public final static String NAME = "NAME";
    public CreateBeginnerRequestFake() {
        this.setAccessToken(new OAuthAccessTokenResponseFake());
        this.setCode(CODE);
        this.setDiscordId(DISCORD_ID);
        this.setName(NAME);
        this.setStarter(new CreateStarterPokemonRequestFake());
    }

}

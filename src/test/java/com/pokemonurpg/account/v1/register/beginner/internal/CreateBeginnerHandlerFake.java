package com.pokemonurpg.account.v1.register.beginner.internal;

import com.pokemonurpg.security.models.DiscordUserResponseFake;
import com.pokemonurpg.security.models.OAuthAccessTokenResponseFake;

public class CreateBeginnerHandlerFake extends CreateBeginnerHandler {
    public CreateBeginnerHandlerFake() {
        super(null, null, null, null, null, null);
    }

    public CreateBeginnerResponse handle(CreateBeginnerRequest request) {
        if (OAuthAccessTokenResponseFake.ACCESS_TOKEN.equals(request.getAccessToken().getAccessToken())
            && CreateBeginnerRequestFake.CODE.equals(request.getCode())
            && CreateBeginnerRequestFake.NAME.equals(request.getName())
            && DiscordUserResponseFake.ID.equals(request.getDiscordId())
            && CreateStarterPokemonRequestFake.GENDER.equals(request.getStarter().getGender())
            && CreateStarterPokemonRequestFake.SPECIES.equals(request.getStarter().getSpecies())
        ) {
            return new CreateBeginnerResponseFake();
        }
        else {
            throw new AssertionError("No test case was supplied for the provided request format.");
        }
    }
}

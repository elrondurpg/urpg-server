package com.pokemonurpg.account.v1.register.beginner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateStarterPokemonResponseFake;

public class RegisterBeginnerResponseFake extends RegisterBeginnerResponse {
    public final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    public final static String DISCORD_ID = "DISCORD_ID";
    public final static String NAME = "NAME";
    public final static String ROLE = "ROLE";

    public RegisterBeginnerResponseFake() {
        this.setAccessToken(ACCESS_TOKEN);
        this.setDiscordId(DISCORD_ID);
        this.setName(NAME);
        this.setRoles(Collections.singletonList(ROLE));
        this.setStarter(new CreateStarterPokemonResponseFake());
    }    

    public static void assertMatchesDefaults(RegisterBeginnerResponse response) {
        assertEquals(RegisterBeginnerResponseFake.ACCESS_TOKEN, response.getAccessToken());
        assertEquals(RegisterBeginnerResponseFake.NAME, response.getName());
        assertEquals(RegisterBeginnerResponseFake.ROLE, response.getRoles().get(0));
        assertEquals(RegisterBeginnerResponseFake.DISCORD_ID, response.getDiscordId());
        CreateStarterPokemonResponseFake.assertMatchesDefaults(response.getStarter());
    }
}

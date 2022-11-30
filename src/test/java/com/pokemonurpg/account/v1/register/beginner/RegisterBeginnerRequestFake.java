package com.pokemonurpg.account.v1.register.beginner;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateStarterPokemonRequestFake;

public class RegisterBeginnerRequestFake extends RegisterBeginnerRequest {
    public final static String CODE = "CODE";
    public final static String NAME = "NAME";

    public RegisterBeginnerRequestFake() {
        this.setCode(CODE);
        this.setName(NAME);
        this.setStarter(new CreateStarterPokemonRequestFake());
    }
}

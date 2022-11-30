package com.pokemonurpg.account.v1.register.beginner.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateStarterPokemonResponseFake extends CreateStarterPokemonResponse {
    public final static String GENDER = "GENDER";
    public final static String SPECIES = "SPECIES";

    public CreateStarterPokemonResponseFake() {
        this.setGender(GENDER);
        this.setSpecies(SPECIES);
    }

    public static void assertMatchesDefaults(CreateStarterPokemonResponse response) {
        assertEquals(GENDER, response.getGender());
        assertEquals(SPECIES, response.getSpecies());
    }
}

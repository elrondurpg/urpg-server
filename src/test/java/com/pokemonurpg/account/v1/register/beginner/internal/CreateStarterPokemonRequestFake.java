package com.pokemonurpg.account.v1.register.beginner.internal;

public class CreateStarterPokemonRequestFake extends CreateStarterPokemonRequest {
    public final static String GENDER = "GENDER";
    public final static String SPECIES = "SPECIES";

    public CreateStarterPokemonRequestFake() {
        this.setGender(GENDER);
        this.setSpecies(SPECIES);
    }
    
}

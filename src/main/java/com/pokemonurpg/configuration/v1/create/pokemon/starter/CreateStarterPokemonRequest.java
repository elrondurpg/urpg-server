package com.pokemonurpg.configuration.v1.create.pokemon.starter;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateStarterPokemonRequest {

    @NotNull
    @ExistsInDb(type = Species.class)
    protected String species;

    @NotNull
    @Pattern(regexp = "^(F|M|G)$")
    protected String gender;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

package com.pokemonurpg.stats.v1;


import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.entities.v1.Pokemon;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StarterPokemonRequest {

    @NotNull
    @ExistsInDb(type = Pokemon.class)
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

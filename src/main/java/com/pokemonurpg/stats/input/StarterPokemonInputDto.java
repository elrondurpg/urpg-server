package com.pokemonurpg.stats.input;


import com.pokemonurpg.lib.validation.annotation.ExistsInDb;
import com.pokemonurpg.entities.Species;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StarterPokemonInputDto {

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
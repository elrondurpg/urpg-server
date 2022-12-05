package com.pokemonurpg.configuration.v1.pokemon.species.input;

import com.pokemonurpg.entities.v1.pokemon.Ability;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import org.jetbrains.annotations.NotNull;

public class SpeciesAbilityInputDto extends ChildInputDto {

    @NotNull
    @ExistsInDb(type = Ability.class)
    private String name;

    private Boolean hidden;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}

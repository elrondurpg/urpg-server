package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.entities.Ability;
import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;
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

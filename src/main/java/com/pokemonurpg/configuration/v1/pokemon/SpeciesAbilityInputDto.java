package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.lib.v1.request.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
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

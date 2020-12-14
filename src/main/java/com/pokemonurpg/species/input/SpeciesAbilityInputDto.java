package com.pokemonurpg.species.input;

import com.pokemonurpg.ability.models.Ability;
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

package com.pokemonurpg.stats.input;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.constraints.NotNull;

public class OwnedHiddenAbilityInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Ability.class)
    private String ability;

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}

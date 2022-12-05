package com.pokemonurpg.stats.input;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.entities.v1.pokemon.Ability;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

public class WishlistAbilityInputDto extends ChildInputDto {
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

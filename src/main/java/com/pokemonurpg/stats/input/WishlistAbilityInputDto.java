package com.pokemonurpg.stats.input;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.entities.Ability;
import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;

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

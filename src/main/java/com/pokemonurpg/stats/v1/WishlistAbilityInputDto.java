package com.pokemonurpg.stats.v1;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.lib.v1.request.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

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

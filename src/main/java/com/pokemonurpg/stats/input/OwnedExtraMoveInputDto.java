package com.pokemonurpg.stats.input;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.species.models.Species;

import javax.validation.constraints.NotNull;

public class OwnedExtraMoveInputDto  extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Attack.class)
    private String attack;

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }
}

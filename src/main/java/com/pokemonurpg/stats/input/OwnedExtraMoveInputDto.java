package com.pokemonurpg.stats.input;

import com.pokemonurpg.entities.Attack;
import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;

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

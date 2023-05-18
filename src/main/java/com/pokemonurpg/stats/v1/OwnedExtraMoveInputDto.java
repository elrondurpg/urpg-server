package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.lib.v1.requests.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

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

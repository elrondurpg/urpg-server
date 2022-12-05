package com.pokemonurpg.stats.input;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

public class WishlistMoveInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Attack.class) 
    private String move;

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    
}

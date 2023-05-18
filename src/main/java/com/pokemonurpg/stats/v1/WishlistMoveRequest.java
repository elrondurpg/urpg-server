package com.pokemonurpg.stats.v1;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.lib.v1.requests.ChildRequest;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

public class WishlistMoveRequest extends ChildRequest {
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

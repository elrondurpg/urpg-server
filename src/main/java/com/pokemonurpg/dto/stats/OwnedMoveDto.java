package com.pokemonurpg.dto.stats;

import com.pokemonurpg.object.OwnedExtraMove;

public class OwnedMoveDto {
    private String name;
    private String method;

    public OwnedMoveDto() {
    }

    public OwnedMoveDto(OwnedExtraMove ownedExtraMove) {
        if (ownedExtraMove != null) {
            setName(ownedExtraMove.getAttack().getName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

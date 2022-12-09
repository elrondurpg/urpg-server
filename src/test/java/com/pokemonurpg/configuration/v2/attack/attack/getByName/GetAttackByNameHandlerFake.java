package com.pokemonurpg.configuration.v2.attack.attack.getByName;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponse;
import com.pokemonurpg.configuration.v2.shared.handler.GetByNameInputBoundary;
import com.pokemonurpg.entities.v1.attack.Attack;

import lombok.Getter;

@Getter
public class GetAttackByNameHandlerFake implements GetByNameInputBoundary<GetAttackResponse> {
    public final static GetAttackResponse RESPONSE = new Attack();
    public final static String NAME = "NAME";

    private String nameArg;

    @Override
    public GetAttackResponse getByName(String name) {
        nameArg = name;
        if (NAME.equalsIgnoreCase(name)) {
            return RESPONSE;
        }
        return null;
    }
    
}

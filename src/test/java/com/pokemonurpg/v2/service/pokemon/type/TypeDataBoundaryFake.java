package com.pokemonurpg.v2.service.pokemon.type;

import com.pokemonurpg.v2.entities.TypeToTest;
import com.pokemonurpg.v2.entities.pokemon.Type;

public class TypeDataBoundaryFake implements TypeDataBoundary {
    public final static Type TYPE = TypeToTest.builder().dbid(TypeToTest.DBID).name(TypeToTest.NAME).build();

    public Type create(Type model) {
        if (TypeToTest.NAME.equals(model.getName())) {
            return TYPE;
        }
        else return null;
    }
}

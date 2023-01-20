package com.pokemonurpg.v2.service.pokemon.type;

import com.pokemonurpg.v2.entities.TypeToTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTypeResponseTest {

    @Test
    public void constructor() {
        TypeToTest entity = new TypeToTest();
        CreateTypeResponse response = new CreateTypeResponse(entity);
        assertEquals(entity.getDbid(), response.getDbid());
        assertEquals(entity.getName(), response.getName());
    }

}
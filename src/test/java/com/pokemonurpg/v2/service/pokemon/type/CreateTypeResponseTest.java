package com.pokemonurpg.v2.service.pokemon.type;

import com.pokemonurpg.v2.entities.TypeToTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTypeResponseTest {

    @Test
    public void constructor() {
        TypeToTest entity = TypeToTest
            .builder()
            .dbid(TypeToTest.DBID)
            .name(TypeToTest.NAME)
            .build();

        CreateTypeResponse response = new CreateTypeResponse(entity);
        assertEquals(TypeToTest.DBID, response.getDbid());
        assertEquals(TypeToTest.NAME, response.getName());
    }

}
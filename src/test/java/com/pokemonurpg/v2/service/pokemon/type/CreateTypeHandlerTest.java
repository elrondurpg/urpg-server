package com.pokemonurpg.v2.service.pokemon.type;

import com.pokemonurpg.v2.entities.TypeToTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTypeHandlerTest {

    private CreateTypeHandler handler;
    private TypeDataBoundaryFake dataBoundary;

    @BeforeEach
    public void setup() {
        dataBoundary = new TypeDataBoundaryFake();
        handler = new CreateTypeHandler(dataBoundary);
    }

    @Test
    public void handle() {
        CreateTypeRequest request = new CreateTypeRequestBuilder()
            .name(TypeToTest.NAME)
            .build();

        CreateTypeResponse response = handler.handle(request);
        assertEquals(TypeToTest.DBID, response.getDbid());
        assertEquals(TypeToTest.NAME, response.getName());
    }

}
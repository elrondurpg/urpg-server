package com.pokemonurpg.v2.domain.pokemon.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTypeHandlerTest {

    private DeleteTypeHandler handler;
    private TypesFake entities;

    @BeforeEach
    public void setup() {
        entities = new TypesFake();
        handler = new DeleteTypeHandler(entities);
    }

    @Test
    public void deleteByDbid_ReturnsDeletedItem() {
        DeleteTypeResponse response = handler.deleteByDbid(TypesFake.EXISTING_DBID);
        assertEquals(TypesFake.EXISTING_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.EXISTING_OUTPUT.getName(), response.getName());
    }

    @Test
    public void deleteByDbid_ReturnsNull() {
        DeleteTypeResponse response = handler.deleteByDbid(TypesFake.NOT_FOUND_DBID);
        assertNull(response);
    }

}
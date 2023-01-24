package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.UnauthorizedException;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundaryFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTypeHandlerTest {

    private DeleteTypeHandler handler;
    private TypesFake entities;
    private AuthorizationInputBoundaryFake sessions;

    @BeforeEach
    public void setup() {
        entities = new TypesFake();
        sessions = new AuthorizationInputBoundaryFake();
        handler = new DeleteTypeHandler(entities, sessions);
    }

    @Test
    public void deleteByDbid_ReturnsDeletedItem() {
        DeleteTypeResponse response = handler.deleteByDbid(TypesFake.EXISTING_DBID);
        assertTrue(sessions.isChecked());
        assertEquals(TypesFake.EXISTING_DBID, response.getDbid());
        assertEquals(TypesFake.EXISTING_NAME, response.getName());
    }

    @Test
    public void deleteByDbid_ReturnsNull() {
        DeleteTypeResponse response = handler.deleteByDbid(TypesFake.NOT_FOUND_DBID);
        assertTrue(sessions.isChecked());
        assertNull(response);
    }

    @Test
    public void handle_unauthorized() {
        sessions.setAuthorized(false);
        assertThrows(UnauthorizedException.class, () -> handler.deleteByDbid(TypesFake.EXISTING_DBID));
    }

}
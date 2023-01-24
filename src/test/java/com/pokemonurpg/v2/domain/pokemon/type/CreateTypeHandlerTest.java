package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.lib.exception.UnauthorizedException;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundaryFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateTypeHandlerTest {

    private CreateTypeHandler handler;
    private TypesFake entities;
    private AuthorizationInputBoundaryFake sessions;

    @BeforeEach
    public void setup() {
        entities = new TypesFake();
        sessions = new AuthorizationInputBoundaryFake();
        handler = new CreateTypeHandler(entities, sessions);
    }

    @Test
    public void handle_valid() {
        CreateTypeRequest request = new CreateTypeRequest();
        request.setName(TypeValidatorFake.VALID_NAME);

        CreateTypeResponse response = handler.create(request);
        assertTrue(sessions.isChecked());
        assertEquals(request.getName(), entities.getSavedInput().getName());
        assertEquals(TypesFake.NEW_DBID, response.getDbid());
        assertEquals(TypesFake.NEW_NAME, response.getName());
    }

    @Test
    public void handle_invalid() {
        CreateTypeRequest request = new CreateTypeRequest();
        request.setName(TypeValidatorFake.INVALID_NAME);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> handler.create(request));
        assertTrue(sessions.isChecked());
        assertEquals(TypeValidatorFake.ERROR, exception.getErrors().get(0));
    }

    @Test
    public void handle_unauthorized() {
        sessions.setAuthorized(false);
        assertThrows(UnauthorizedException.class, () -> handler.create(new CreateTypeRequest()));
    }

}
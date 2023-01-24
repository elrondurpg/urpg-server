package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.lib.exception.NotFoundException;
import com.pokemonurpg.v2.lib.exception.UnauthorizedException;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundaryFake;
import com.pokemonurpg.v2.entities.pokemon.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.pokemonurpg.v2.lib.constants.ErrorConstants.NOT_FOUND_FORMATTABLE_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class UpdateTypeHandlerTest {

    private UpdateTypeHandler handler;
    private TypesFake entities;
    private AuthorizationInputBoundaryFake sessions;

    @BeforeEach
    public void setup() {
        entities = new TypesFake();
        sessions = new AuthorizationInputBoundaryFake();
        handler = new UpdateTypeHandler(entities, sessions);
    }

    @Test
    public void handle_valid() {
        UpdateTypeRequest request = new UpdateTypeRequest();
        request.setName(TypeValidatorFake.VALID_NAME);

        UpdateTypeResponse response = handler.update(TypesFake.EXISTING_DBID, request);
        assertTrue(sessions.isChecked());
        assertEquals(TypesFake.EXISTING_DBID, entities.getSavedInput().getDbid());
        assertEquals(request.getName(), entities.getSavedInput().getName());
        assertEquals(TypesFake.NEW_DBID, response.getDbid());
        assertEquals(TypesFake.NEW_NAME, response.getName());
    }

    @Test
    public void handle_not_found() {
        UpdateTypeRequest request = new UpdateTypeRequest();
        request.setName(TypeValidatorFake.VALID_NAME);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> handler.update(TypesFake.NOT_FOUND_DBID, request));
        assertTrue(sessions.isChecked());
        assertEquals(String.format(NOT_FOUND_FORMATTABLE_MESSAGE, Type.class, "dbid", TypesFake.NOT_FOUND_DBID), exception.getMessage());
    }

    @Test
    public void handle_invalid() {
        UpdateTypeRequest request = new UpdateTypeRequest();
        request.setName(TypeValidatorFake.INVALID_NAME);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> handler.update(TypesFake.EXISTING_DBID, request));
        assertTrue(sessions.isChecked());
        assertEquals(TypeValidatorFake.ERROR, exception.getErrors().get(0));
    }

    @Test
    public void handle_unauthorized() {
        sessions.setAuthorized(false);
        assertThrows(UnauthorizedException.class, () -> handler.update(TypesFake.EXISTING_DBID, new UpdateTypeRequest()));
    }
}
package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.domain.lib.exception.NotFoundException;
import com.pokemonurpg.v2.entities.pokemon.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.pokemonurpg.v2.domain.lib.constants.ErrorConstants.NOT_FOUND_FORMATTABLE_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class UpdateTypeHandlerTest {

    private UpdateTypeHandler handler;
    private TypesFake types;

    @BeforeEach
    public void setup() {
        types = new TypesFake();
        handler = new UpdateTypeHandler(types);
    }

    @Test
    public void handle_valid() {
        UpdateTypeRequest request = new UpdateTypeRequest();
        request.setName(TypeValidatorFake.VALID_NAME);

        UpdateTypeResponse response = handler.handle(TypesFake.EXISTING_DBID, request);
        assertEquals(TypesFake.EXISTING_DBID, types.getSavedInput().getDbid());
        assertEquals(request.getName(), types.getSavedInput().getName());
        assertEquals(TypesFake.SAVED_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.SAVED_OUTPUT.getName(), response.getName());
    }

    @Test
    public void handle_not_found() {
        UpdateTypeRequest request = new UpdateTypeRequest();
        request.setName(TypeValidatorFake.VALID_NAME);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> handler.handle(TypesFake.NOT_FOUND_DBID, request));
        assertEquals(String.format(NOT_FOUND_FORMATTABLE_MESSAGE, Type.class, "dbid", TypesFake.NOT_FOUND_DBID), exception.getMessage());
    }

    @Test
    public void handle_invalid() {
        UpdateTypeRequest request = new UpdateTypeRequest();
        request.setName(TypeValidatorFake.INVALID_NAME);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> handler.handle(TypesFake.EXISTING_DBID, request));
        assertEquals(types.getValidator().getErrors(), exception.getErrors());
    }
}
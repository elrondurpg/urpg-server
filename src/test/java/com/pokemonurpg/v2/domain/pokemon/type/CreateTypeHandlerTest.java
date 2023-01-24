package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateTypeHandlerTest {

    private CreateTypeHandler handler;
    private TypesFake types;

    @BeforeEach
    public void setup() {
        types = new TypesFake();
        handler = new CreateTypeHandler(types);
    }

    @Test
    public void handle_valid() {
        CreateTypeRequest request = new CreateTypeRequest();
        request.setName(TypeValidatorFake.VALID_NAME);

        CreateTypeResponse response = handler.handle(request);
        assertEquals(request.getName(), types.getSavedInput().getName());
        assertEquals(TypesFake.SAVED_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.SAVED_OUTPUT.getName(), response.getName());
    }

    @Test
    public void handle_invalid() {
        CreateTypeRequest request = new CreateTypeRequest();
        request.setName(TypeValidatorFake.INVALID_NAME);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> handler.handle(request));
        assertEquals(types.getValidator().getErrors(), exception.getErrors());
    }

}
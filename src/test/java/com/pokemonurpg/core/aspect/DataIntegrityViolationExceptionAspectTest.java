package com.pokemonurpg.core.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class DataIntegrityViolationExceptionAspectTest {

    private DataIntegrityViolationExceptionAspect dataIntegrityViolationExceptionAspect = new DataIntegrityViolationExceptionAspect();

    @Test
    public void throwsResponseStatusException () {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> 
            dataIntegrityViolationExceptionAspect.afterThrowingDataIntegrityViolationException(mock(DataIntegrityViolationException.class)));
    }

}
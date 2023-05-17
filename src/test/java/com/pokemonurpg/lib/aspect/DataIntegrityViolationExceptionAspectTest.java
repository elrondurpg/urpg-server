package com.pokemonurpg.lib.aspect;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.mock;

public class DataIntegrityViolationExceptionAspectTest {

    private DataIntegrityViolationExceptionAspect dataIntegrityViolationExceptionAspect = new DataIntegrityViolationExceptionAspect();

    @Test(expected = ResponseStatusException.class)
    public void throwsResponseStatusException () {
        dataIntegrityViolationExceptionAspect.afterThrowingDataIntegrityViolationException(mock(DataIntegrityViolationException.class));
    }

}
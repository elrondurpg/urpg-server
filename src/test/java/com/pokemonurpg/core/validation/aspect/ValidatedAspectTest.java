package com.pokemonurpg.core.validation.annotation;

import com.pokemonurpg.core.validation.ValidationService;
import com.pokemonurpg.core.validation.aspect.ValidatedAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidatedAspectTest {
    @InjectMocks
    private ValidatedAspect validatedAspect;

    @Mock
    private ValidationService validationService;

    private Object obj = new Object();

    @Test
    public void validObjectThrowsNoError() throws IllegalAccessException {
        when(validationService.validate(obj)).thenReturn(true);
        validatedAspect.before(obj);
    }

    @Test(expected = IllegalStateException.class)
    public void invalidObjectThrowsError() throws IllegalAccessException {
        when(validationService.validate(obj)).thenReturn(false);
        validatedAspect.before(obj);
    }

    @Test(expected = IllegalStateException.class)
    public void illegalAccessExceptionThrowsError() throws IllegalAccessException {
        when(validationService.validate(obj)).thenThrow(IllegalAccessException.class);
        validatedAspect.before(obj);
    }
}
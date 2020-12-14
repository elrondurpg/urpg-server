package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.NamedObjectServiceFactory;
import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.ability.service.AbilityService;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExistsInDbStringValidatorTest {
    private static final String ABILITY_NAME = "Cacophony";

    @InjectMocks
    private ExistsInDbStringValidator existsInDbValidator = new ExistsInDbStringValidator();

    @Mock
    private NamedObjectServiceFactory namedObjectServiceFactory;

    @Mock
    private AbilityService abilityService;

    @ExistsInDb(type = Ability.class)
    private String string;

    @Test
    public void returnsTrueWhenNull() {
        assertTrue(existsInDbValidator.isValid(null, null));
    }

    @Test
    public void initialize() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("string").getAnnotation(ExistsInDb.class));
        assertEquals(Ability.class, existsInDbValidator.getType());
    }

    @Test
    public void returnsFalseWhenUnique() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("string").getAnnotation(ExistsInDb.class));
        when(namedObjectServiceFactory.getServiceForClass(Ability.class)).thenReturn(abilityService);
        when(abilityService.findByName(ABILITY_NAME)).thenReturn(null);

        assertFalse(existsInDbValidator.isValid(ABILITY_NAME, null));
    }

    @Test
    public void returnsTrueWhenNotUnique() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("string").getAnnotation(ExistsInDb.class));
        when(namedObjectServiceFactory.getServiceForClass(Ability.class)).thenReturn(abilityService);
        when(abilityService.findByName(ABILITY_NAME)).thenReturn(new Ability());

        assertTrue(existsInDbValidator.isValid(ABILITY_NAME, null));
    }

    @Test
    public void returnsFalseOnException() {
        assertFalse(existsInDbValidator.isValid(ABILITY_NAME, null));
    }
}
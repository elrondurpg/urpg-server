package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.v1.service.NamedObjectServiceFactory;
import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.configuration.v1.abilities.AbilityService;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.lib.v1.validators.ExistsInDbStringValidator;
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
        when(abilityService.findByNameExact(ABILITY_NAME)).thenReturn(null);

        assertFalse(existsInDbValidator.isValid(ABILITY_NAME, null));
    }

    @Test
    public void returnsTrueWhenNotUnique() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("string").getAnnotation(ExistsInDb.class));
        when(namedObjectServiceFactory.getServiceForClass(Ability.class)).thenReturn(abilityService);
        when(abilityService.findByNameExact(ABILITY_NAME)).thenReturn(new Ability());

        assertTrue(existsInDbValidator.isValid(ABILITY_NAME, null));
    }

    @Test
    public void returnsFalseOnException() {
        assertFalse(existsInDbValidator.isValid(ABILITY_NAME, null));
    }
}
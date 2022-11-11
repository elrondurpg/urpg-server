package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.core.service.NamedObjectServiceFactory;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

    /*@Test
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
    }*/

    @Test
    public void returnsFalseOnException() {
        assertFalse(existsInDbValidator.isValid(ABILITY_NAME, null));
    }
}
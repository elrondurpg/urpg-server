package com.pokemonurpg.stats.validation;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LegendaryProgressInputSequenceProviderTest {

    LegendaryProgressInputSequenceProvider legendaryProgressInputSequenceProvider = new LegendaryProgressInputSequenceProvider();

    @Test
    public void getValidationGroups() {
        LegendaryProgressInputDto input = new LegendaryProgressInputDto();

        List<Class<?>> sequence = legendaryProgressInputSequenceProvider.getValidationGroups(input);

        assertTrue(sequence.contains(ObjectCreation.class));
    }

}
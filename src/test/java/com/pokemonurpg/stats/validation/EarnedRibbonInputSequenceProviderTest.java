package com.pokemonurpg.stats.validation;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EarnedRibbonInputSequenceProviderTest {

    EarnedRibbonInputSequenceProvider earnedRibbonInputSequenceProvider = new EarnedRibbonInputSequenceProvider();

    @Test
    public void getValidationGroups() {
        EarnedRibbonInputDto input = new EarnedRibbonInputDto();

        List<Class<?>> sequence = earnedRibbonInputSequenceProvider.getValidationGroups(input);

        assertTrue(sequence.contains(ObjectCreation.class));
    }

}
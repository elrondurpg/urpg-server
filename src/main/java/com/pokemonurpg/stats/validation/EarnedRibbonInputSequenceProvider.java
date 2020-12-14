package com.pokemonurpg.stats.validation;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class EarnedRibbonInputSequenceProvider implements DefaultGroupSequenceProvider<EarnedRibbonInputDto> {
    @Override
    public List<Class<?>> getValidationGroups(EarnedRibbonInputDto input) {
        List<Class<?>> sequence = new ArrayList<>();

        // Applies the ObjectCreation validation group if the input's DBID field is null
        if (input != null) {
            if (input.getDbid() == null) {
                sequence.add(ObjectCreation.class);
            }
        }

        // Apply all default validation rules
        sequence.add(EarnedRibbonInputDto.class);

        return sequence;
    }
}

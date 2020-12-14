package com.pokemonurpg.stats.validation;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class LegendaryProgressInputSequenceProvider implements DefaultGroupSequenceProvider<LegendaryProgressInputDto> {
    @Override
    public List<Class<?>> getValidationGroups(LegendaryProgressInputDto legendaryProgressInputDto) {
        List<Class<?>> sequence = new ArrayList<>();

        // Applies the ObjectCreation validation group if the input's DBID field is null
        if (legendaryProgressInputDto != null) {
            if (legendaryProgressInputDto.getDbid() == null) {
                sequence.add(ObjectCreation.class);
            }
        }

        // Apply all default validation rules
        sequence.add(LegendaryProgressInputDto.class);

        return sequence;
    }
}

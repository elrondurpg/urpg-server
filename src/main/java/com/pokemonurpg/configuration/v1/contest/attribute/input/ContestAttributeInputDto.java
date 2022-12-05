package com.pokemonurpg.configuration.v1.contest.attribute.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

@UniqueName(type = ContestAttribute.class)
@Getter
@Setter
public class ContestAttributeInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 15)
    private String name;
}

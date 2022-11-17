package com.pokemonurpg.configuration.v1.contest.type.input;

import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@UniqueName(type = ContestType.class)
public class ContestTypeInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;
}

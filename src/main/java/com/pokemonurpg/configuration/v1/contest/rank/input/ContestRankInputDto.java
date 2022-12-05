package com.pokemonurpg.configuration.v1.contest.rank.input;

import com.pokemonurpg.entities.v1.contest.ContestRank;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ContestRank.class)
@Getter
@Setter
public class ContestRankInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 15)
    private String name;
}

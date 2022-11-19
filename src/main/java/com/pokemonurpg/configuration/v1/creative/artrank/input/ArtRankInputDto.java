package com.pokemonurpg.configuration.v1.creative.artrank.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.creative.artrank.model.ArtRank;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ArtRank.class)
@Getter
@Setter
public class ArtRankInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    private String requirement;
}

package com.pokemonurpg.configuration.v1.gym.champion.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.BelongsToThisChampionSlot;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

@UniqueName(type = Champion.class)
@Getter
@Setter
public class ChampionInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @AllPokemonBelongToOwner
    private List<@Valid ChampionPokemonInputDto> pokemon = new ArrayList<>();

    @BelongsToThisChampionSlot
    private Integer currentOwnerRecordDbid;

    private Boolean removeOwner = null;
}

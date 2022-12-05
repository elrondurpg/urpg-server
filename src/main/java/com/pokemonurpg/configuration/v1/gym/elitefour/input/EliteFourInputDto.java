package com.pokemonurpg.configuration.v1.gym.elitefour.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.gym.EliteFour;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.BelongsToThisEliteFourSlot;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = EliteFour.class)
@Getter
@Setter
public class EliteFourInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @AllPokemonBelongToOwner
    private List<@Valid EliteFourPokemonInputDto> pokemon = new ArrayList<>();

    @BelongsToThisEliteFourSlot
    private Integer currentOwnerRecordDbid;

    private Boolean removeOwner = null;
}

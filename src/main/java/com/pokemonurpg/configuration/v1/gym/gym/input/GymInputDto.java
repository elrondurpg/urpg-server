package com.pokemonurpg.configuration.v1.gym.gym.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.pokemon.Type;
import com.pokemonurpg.entities.v1.gym.Badge;
import com.pokemonurpg.entities.v1.gym.Gym;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.BelongsToThisGym;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Gym.class)
@Getter
@Setter
public class GymInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Badge.class)
    private String badge;

    @ExistsInDb(type = Type.class)
    private String type;

    @AllPokemonBelongToOwner
    private List<@Valid GymPokemonInputDto> pokemon = new ArrayList<>();

    @BelongsToThisGym
    private Integer currentOwnerRecordDbid;

    private Boolean removeOwner = null;
}

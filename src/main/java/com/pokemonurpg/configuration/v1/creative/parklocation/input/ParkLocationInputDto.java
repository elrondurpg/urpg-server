package com.pokemonurpg.configuration.v1.creative.parklocation.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.creative.ParkLocation;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ParkLocation.class)
@Getter
@Setter
public class ParkLocationInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 25)
    private String name;
}

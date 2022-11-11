package com.pokemonurpg.configuration.v1.pokemon.type.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Type.class)
@Getter
@Setter
public class TypeInputDto extends NamedConfigurationInputDto {
    @NotNull
    @Size(min = 3, max = 10)
    private String name;
}

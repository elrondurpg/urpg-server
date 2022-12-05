package com.pokemonurpg.configuration.v1.attack.target.input;


import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.attack.AttackTargetType;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = AttackTargetType.class)
@Getter
@Setter
public class AttackTargetTypeInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 35)
    private String name;

    @Size(min = 3, max = 100)
    private String description;
}

package com.pokemonurpg.configuration.v1.gym.badge.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.gym.Badge;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Badge.class)
@Getter
@Setter
public class BadgeInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max=20)
    private String name;
}

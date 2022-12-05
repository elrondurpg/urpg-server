package com.pokemonurpg.configuration.v1.attack.category.input;

import com.pokemonurpg.entities.v1.attack.AttackCategory;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = AttackCategory.class)
@Getter
@Setter
public class AttackCategoryInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    private String name;
}

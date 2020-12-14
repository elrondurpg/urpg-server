package com.pokemonurpg.ability.input;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AbilityInputDto extends AuthenticatedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 25)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 160)
    private String description;

    public AbilityInputDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

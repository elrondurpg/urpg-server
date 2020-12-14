package com.pokemonurpg.gym.input;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BadgeInputDto extends AuthenticatedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max=20)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

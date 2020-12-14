package com.pokemonurpg.attack.input;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AttackCategoryInputDto extends AuthenticatedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

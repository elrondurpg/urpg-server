package com.pokemonurpg.species.input;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TypeInputDto extends AuthenticatedInputDto {
    @NotNull
    @Size(min = 3, max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.pokemonurpg.configuration.v1.contestgenerations;

import com.pokemonurpg.entities.v1.ContestType;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ContestType.class)
public class ContestTypeInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

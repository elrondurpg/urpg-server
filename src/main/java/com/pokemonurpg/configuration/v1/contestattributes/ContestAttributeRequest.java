package com.pokemonurpg.configuration.v1.contestattributes;

import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ContestAttribute.class)
public class ContestAttributeRequest implements UniquelyNamedRequest {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 15)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

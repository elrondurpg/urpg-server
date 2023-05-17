package com.pokemonurpg.configuration.v1.natures;


import com.pokemonurpg.entities.Nature;
import com.pokemonurpg.lib.input.UniquelyNamedInputDto;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.lib.validation.annotation.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Nature.class)
public class NatureInputDto implements UniquelyNamedInputDto {

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

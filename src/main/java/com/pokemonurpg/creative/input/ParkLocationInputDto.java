package com.pokemonurpg.creative.input;

import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.creative.models.ParkLocation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ParkLocation.class)
public class ParkLocationInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 25)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

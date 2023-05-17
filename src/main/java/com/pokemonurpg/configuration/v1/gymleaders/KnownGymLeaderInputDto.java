package com.pokemonurpg.configuration.v1.gymleaders;

import com.pokemonurpg.entities.KnownGymLeader;
import com.pokemonurpg.lib.input.UniquelyNamedInputDto;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.lib.validation.annotation.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = KnownGymLeader.class)
public class KnownGymLeaderInputDto implements UniquelyNamedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    public KnownGymLeaderInputDto() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

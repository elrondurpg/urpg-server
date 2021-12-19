package com.pokemonurpg.gym.input;

import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.gym.models.KnownGymLeader;

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

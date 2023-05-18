package com.pokemonurpg.configuration.v1.gymleaders;

import com.pokemonurpg.entities.v1.GymLeader;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = GymLeader.class)
public class GymLeaderRequest implements UniquelyNamedRequest {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    public GymLeaderRequest() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.pokemonurpg.configuration.v1.storyranks;


import com.pokemonurpg.entities.v1.StoryRank;
import com.pokemonurpg.lib.v1.request.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = StoryRank.class)
public class StoryRankInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    private String requirement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}

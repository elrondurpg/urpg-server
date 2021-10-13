package com.pokemonurpg.creative.input;


import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.creative.models.StoryRank;

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

package com.pokemonurpg.contest.input;

import com.pokemonurpg.core.validation.ObjectCreation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContestRankInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 6)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

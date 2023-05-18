package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.entities.v1.ContestMoveType;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ContestMoveType.class)
public class ContestMoveTypeInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 100)
    private String description;

    @NotNull(groups = { ObjectCreation.class })
    @Min(-1)
    @Max(8)
    private Integer score;

    @NotNull(groups = { ObjectCreation.class })
    @Min(-1)
    @Max(8)
    private Integer jam;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getJam() {
        return jam;
    }

    public void setJam(Integer jam) {
        this.jam = jam;
    }
}

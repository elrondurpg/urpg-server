package com.pokemonurpg.configuration.v1.contesteffects;

import com.pokemonurpg.entities.v1.ContestEffect;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = ContestEffect.class)
@Getter
@Setter
public class ContestEffectRequest implements UniquelyNamedRequest {
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
}

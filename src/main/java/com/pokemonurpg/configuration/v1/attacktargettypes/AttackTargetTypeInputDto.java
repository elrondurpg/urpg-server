package com.pokemonurpg.configuration.v1.attacktargettypes;

import com.pokemonurpg.entities.v1.AttackTargetType;
import com.pokemonurpg.lib.v1.request.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = AttackTargetType.class)
public class AttackTargetTypeInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 35)
    private String name;

    @Size(min = 3, max = 100)
    private String description;

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
}

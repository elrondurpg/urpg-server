package com.pokemonurpg.configuration.v1.member.permission;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.entities.v1.member.Permission;

@UniqueName(type = Permission.class)
public class PermissionInputDto implements UniquelyNamedInputDto {

    @NotNull(groups = {ObjectCreation.class})
    @Size(min = 3, max = 20)
    private String name;

    @NotNull(groups = {ObjectCreation.class})
    @Size(min = 3, max = 60)
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

package com.pokemonurpg.configuration.v1.roles;

import com.pokemonurpg.entities.v1.Permission;
import com.pokemonurpg.lib.v1.requests.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

import javax.validation.constraints.NotNull;

public class RolePermissionInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Permission.class)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

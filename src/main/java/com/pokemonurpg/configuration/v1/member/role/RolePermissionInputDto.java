package com.pokemonurpg.member.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.member.models.Permission;

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

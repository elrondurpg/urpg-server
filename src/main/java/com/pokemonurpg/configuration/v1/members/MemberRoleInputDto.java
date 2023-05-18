package com.pokemonurpg.configuration.v1.members;

import com.pokemonurpg.entities.v1.Role;
import com.pokemonurpg.lib.v1.requests.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

import javax.validation.constraints.NotNull;

public class MemberRoleInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Role.class)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

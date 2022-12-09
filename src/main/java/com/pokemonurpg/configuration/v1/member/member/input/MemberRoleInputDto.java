package com.pokemonurpg.configuration.v1.member.member.input;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.entities.v1.member.Role;

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

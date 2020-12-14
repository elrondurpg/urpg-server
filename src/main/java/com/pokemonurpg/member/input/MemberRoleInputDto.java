package com.pokemonurpg.member.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.member.models.Role;

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

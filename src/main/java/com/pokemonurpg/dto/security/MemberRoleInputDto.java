package com.pokemonurpg.dto.security;

import com.pokemonurpg.dto.InputDto;

public class MemberRoleInputDto extends InputDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

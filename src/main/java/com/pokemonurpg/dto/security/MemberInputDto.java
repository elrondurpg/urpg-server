package com.pokemonurpg.dto.security;

import com.pokemonurpg.dto.InputDto;

import java.util.List;

public class MemberInputDto extends InputDto {

    String name;
    List<MemberRoleInputDto> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberRoleInputDto> getRoles() {
        return roles;
    }

    public void setRoles(List<MemberRoleInputDto> roles) {
        this.roles = roles;
    }
}

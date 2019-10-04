package com.pokemonurpg.dto.security;

import com.pokemonurpg.object.Member;

import java.util.List;

public class MemberDto {

    private String username;
    private List<String> roles;

    public MemberDto(Member member) {
        if (member != null) {
            setUsername(member.getUsername());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

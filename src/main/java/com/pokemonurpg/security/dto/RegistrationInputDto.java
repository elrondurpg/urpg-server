package com.pokemonurpg.security.dto;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.DoesNotConflictWithKnownGymLeader;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.member.models.Member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@UniqueName(type = Member.class)
public class RegistrationInputDto {
    @NotEmpty
    private String code;

    @NotEmpty
    @DoesNotConflictWithKnownGymLeader(groups = {ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

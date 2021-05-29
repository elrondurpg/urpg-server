package com.pokemonurpg.gym.input;

import com.pokemonurpg.core.input.ChildInputDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KnownEliteFourMemberInputDto extends ChildInputDto {
    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    public KnownEliteFourMemberInputDto() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
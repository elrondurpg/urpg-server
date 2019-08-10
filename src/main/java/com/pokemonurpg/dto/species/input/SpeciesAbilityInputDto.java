package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;

public class SpeciesAbilityInputDto extends InputDto
{
    public String name;
    public boolean hidden;

    public SpeciesAbilityInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}

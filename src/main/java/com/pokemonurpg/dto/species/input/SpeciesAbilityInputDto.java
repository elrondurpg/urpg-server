package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;

public class SpeciesAbilityInputDto extends InputDto
{
    public String name;
    public Boolean hidden;

    public SpeciesAbilityInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}

package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;

public class SpeciesAttackInputDto extends InputDto
{
    private String name;
    private String method;
    private Integer generation;

    public SpeciesAttackInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }
}

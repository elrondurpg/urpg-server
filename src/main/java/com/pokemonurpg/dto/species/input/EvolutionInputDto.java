package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;

public class EvolutionInputDto extends InputDto
{
    private String name;
    private String method;
    private int numBattles;

    public EvolutionInputDto() {
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

    public int getNumBattles() {
        return numBattles;
    }

    public void setNumBattles(int numBattles) {
        this.numBattles = numBattles;
    }
}

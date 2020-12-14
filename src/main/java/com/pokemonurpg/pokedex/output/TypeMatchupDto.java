package com.pokemonurpg.pokedex.output;

public class TypeMatchupDto {
    private String name;
    private Double multiplier;

    public TypeMatchupDto(String type, double multiplier) {
        setName(type);
        setMultiplier(multiplier);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }
}

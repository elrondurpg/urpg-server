package com.pokemonurpg.dto.species.response;

public class TypeMatchupDto {
    String name;
    double multiplier;

    public TypeMatchupDto(String type, double multiplier) {
        this.name = type;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}

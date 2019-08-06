package com.pokemonurpg.dto;

public class TypeMatchupDto {
    String type;
    double multiplier;

    public TypeMatchupDto(String type, double multiplier) {
        this.type = type;
        this.multiplier = multiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}

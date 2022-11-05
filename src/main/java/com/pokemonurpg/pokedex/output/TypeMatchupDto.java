package com.pokemonurpg.pokedex.output;

import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;

public class TypeMatchupDto {
    private Type type;
    private Double multiplier;

    public TypeMatchupDto(Type type, double multiplier) {
        setType(type);
        setMultiplier(multiplier);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }
}

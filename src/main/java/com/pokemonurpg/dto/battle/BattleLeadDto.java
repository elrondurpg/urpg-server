package com.pokemonurpg.dto.battle;

import java.util.List;

public class BattleLeadDto {
    private String name;
    private List<BattleLeadPokemonDto> pokemon;

    public BattleLeadDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BattleLeadPokemonDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<BattleLeadPokemonDto> pokemon) {
        this.pokemon = pokemon;
    }
}

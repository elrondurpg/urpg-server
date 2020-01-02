package com.pokemonurpg.dto.battle;

import java.util.List;

public class BattleTeamDto {
    private String name;
    private List<BattlePokemonDto> pokemon;

    public BattleTeamDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BattlePokemonDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<BattlePokemonDto> pokemon) {
        this.pokemon = pokemon;
    }
}

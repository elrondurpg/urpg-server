package com.pokemonurpg.dto.battle;

public class BattleTrainerDto {
    private String name;
    private Integer team;

    public BattleTrainerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }
}

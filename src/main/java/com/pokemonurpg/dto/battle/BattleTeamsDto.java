package com.pokemonurpg.dto.battle;

import java.util.List;

public class BattleTeamsDto {
    private Long battleDbid;
    private List<BattleTeamDto> trainers;

    public BattleTeamsDto() {
    }

    public Long getBattleDbid() {
        return battleDbid;
    }

    public void setBattleDbid(Long battleDbid) {
        this.battleDbid = battleDbid;
    }

    public List<BattleTeamDto> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<BattleTeamDto> trainers) {
        this.trainers = trainers;
    }
}

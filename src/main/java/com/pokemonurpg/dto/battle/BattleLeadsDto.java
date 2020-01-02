package com.pokemonurpg.dto.battle;

import java.util.List;

public class BattleLeadsDto {
    private Long battleDbid;
    private List<BattleLeadDto> trainers;

    public BattleLeadsDto() {
    }

    public Long getBattleDbid() {
        return battleDbid;
    }

    public void setBattleDbid(Long battleDbid) {
        this.battleDbid = battleDbid;
    }

    public List<BattleLeadDto> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<BattleLeadDto> trainers) {
        this.trainers = trainers;
    }
}
package com.pokemonurpg.object.battle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BattleTrainerKey implements Serializable {
    @Column(name = "trainer_dbid")
    private Integer trainerDbid;

    @Column(name = "battle_dbid")
    private Long battleDbid;

    public BattleTrainerKey() {
    }

    public BattleTrainerKey(int trainerDbid, long battleDbid) {
        setTrainerDbid(trainerDbid);
        setBattleDbid(battleDbid);
    }

    public Integer getTrainerDbid() {
        return trainerDbid;
    }

    public void setTrainerDbid(Integer trainerDbid) {
        this.trainerDbid = trainerDbid;
    }

    public Long getBattleDbid() {
        return battleDbid;
    }

    public void setBattleDbid(Long battleDbid) {
        this.battleDbid = battleDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleTrainerKey that = (BattleTrainerKey) o;
        return Objects.equals(trainerDbid, that.trainerDbid) &&
                Objects.equals(battleDbid, that.battleDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerDbid, battleDbid);
    }
}

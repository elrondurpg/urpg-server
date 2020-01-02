package com.pokemonurpg.object.battle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BattleRefereeKey implements Serializable {
    @Column(name = "referee_dbid")
    private Integer refereeDbid;

    @Column(name = "battle_dbid")
    private Long battleDbid;

    public BattleRefereeKey() {
    }

    public Integer getRefereeDbid() {
        return refereeDbid;
    }

    public void setRefereeDbid(Integer refereeDbid) {
        this.refereeDbid = refereeDbid;
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
        BattleRefereeKey that = (BattleRefereeKey) o;
        return Objects.equals(refereeDbid, that.refereeDbid) &&
                Objects.equals(battleDbid, that.battleDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refereeDbid, battleDbid);
    }
}

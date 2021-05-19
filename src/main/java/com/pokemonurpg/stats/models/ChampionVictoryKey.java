package com.pokemonurpg.stats.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChampionVictoryKey implements Serializable {

    @Column(name = "challenger_dbid")
    private Integer challengerDbid;

    @Column
    private String defender;

    public ChampionVictoryKey() {
    }

    public ChampionVictoryKey(int challengerDbid, String defender) {
        this.challengerDbid = challengerDbid;
        this.defender = defender;
    }

    public Integer getChallengerDbid() {
        return challengerDbid;
    }

    public String getDefender() {
        return defender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionVictoryKey that = (ChampionVictoryKey) o;
        return Objects.equals(challengerDbid, that.challengerDbid) &&
                Objects.equals(defender, that.defender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challengerDbid, defender);
    }
}

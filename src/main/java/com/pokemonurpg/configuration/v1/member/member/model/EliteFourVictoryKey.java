package com.pokemonurpg.configuration.v1.member.member.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EliteFourVictoryKey implements Serializable {

    @Column(name = "challenger_dbid")
    private Integer challengerDbid;

    @Column(name = "defender_dbid")
    private Integer defenderDbid;

    public EliteFourVictoryKey() {

    }

    public EliteFourVictoryKey(Integer challengerDbid, int defenderDbid) {
        this.challengerDbid = challengerDbid;
        this.defenderDbid = defenderDbid;
    }

    public Integer getChallengerDbid() {
        return challengerDbid;
    }

    public Integer getDefenderDbid() {
        return defenderDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EliteFourVictoryKey that = (EliteFourVictoryKey) o;
        return Objects.equals(challengerDbid, that.challengerDbid) && Objects.equals(defenderDbid, that.defenderDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challengerDbid, defenderDbid);
    }
}

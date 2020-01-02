package com.pokemonurpg.object.trainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChampionRecordKey implements Serializable {
    @Column(name = "trainer_dbid")
    private Integer trainerDbid;

    @Column(name = "league_dbid")
    private Integer leagueDbid;

    public ChampionRecordKey() {
    }

    public Integer getTrainerDbid() {
        return trainerDbid;
    }

    public void setTrainerDbid(Integer trainerDbid) {
        this.trainerDbid = trainerDbid;
    }

    public Integer getLeagueDbid() {
        return leagueDbid;
    }

    public void setLeagueDbid(Integer leagueDbid) {
        this.leagueDbid = leagueDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionRecordKey that = (ChampionRecordKey) o;
        return Objects.equals(trainerDbid, that.trainerDbid) &&
                Objects.equals(leagueDbid, that.leagueDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerDbid, leagueDbid);
    }
}

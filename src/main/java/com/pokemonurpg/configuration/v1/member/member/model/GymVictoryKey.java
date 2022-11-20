package com.pokemonurpg.configuration.v1.member.member.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GymVictoryKey implements Serializable {

    @Column(name = "challenger_dbid")
    private Integer challengerDbid;

    @Column(name = "defender_dbid")
    private Integer defenderDbid;

    @Column(name = "gym_dbid")
    private Integer gymDbid;

    @Column(name = "league_dbid")
    private Integer leagueDbid;

    public GymVictoryKey() {
    }

    public GymVictoryKey(int challengerDbid, int defenderDbid, int gymDbid, int leagueDbid) {
        this.challengerDbid = challengerDbid;
        this.defenderDbid = defenderDbid;
        this.gymDbid = gymDbid;
        this.leagueDbid = leagueDbid;
    }

    public Integer getChallengerDbid() {
        return challengerDbid;
    }

    public Integer getDefenderDbid() {
        return defenderDbid;
    }

    public Integer getGymDbid() {
        return gymDbid;
    }

    public Integer getLeagueDbid() {
        return leagueDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymVictoryKey that = (GymVictoryKey) o;
        return Objects.equals(challengerDbid, that.challengerDbid) && Objects.equals(defenderDbid, that.defenderDbid) && Objects.equals(gymDbid, that.gymDbid) && Objects.equals(leagueDbid, that.leagueDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challengerDbid, defenderDbid, gymDbid, leagueDbid);
    }
}
